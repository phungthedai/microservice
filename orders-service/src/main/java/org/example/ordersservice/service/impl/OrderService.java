package org.example.ordersservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ordersservice.client.dto.request.ProductFilter;
import org.example.ordersservice.client.dto.request.PromotionsFindId;
import org.example.ordersservice.client.dto.request.StockProductItemRequest;
import org.example.ordersservice.client.dto.request.StockProductRequest;
import org.example.ordersservice.client.dto.response.ProductResponse;
import org.example.ordersservice.client.dto.response.PromotionsResponse;
import org.example.ordersservice.client.service.PromotionsClient;
import org.example.ordersservice.client.service.impl.ProductClientImpl;
import org.example.ordersservice.common.DiscountType;
import org.example.ordersservice.common.OrderStatus;
import org.example.ordersservice.dto.request.CreateOrderItem;
import org.example.ordersservice.dto.request.OrdersCreateItemRequest;
import org.example.ordersservice.dto.request.OrdersCreateRequest;
import org.example.ordersservice.dto.response.OrderResponse;
import org.example.ordersservice.entity.OrderEntity;
import org.example.ordersservice.entity.OrderItemEntity;
import org.example.ordersservice.exception.ApplicationErrors;
import org.example.ordersservice.exception.ApplicationException;
import org.example.ordersservice.mapper.OrderItemMapper;
import org.example.ordersservice.mapper.OrderMapper;
import org.example.ordersservice.repository.OrderItemRepository;
import org.example.ordersservice.repository.OrderRepository;
import org.example.ordersservice.service.IOrders;
import org.example.ordersservice.service.IOrdersItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements IOrders {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final ProductClientImpl productClient;
    private final PromotionsClient promotionsClient;
    private final IOrdersItem ordersItem;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderResponse create(OrdersCreateRequest ordersCreateRequest) {
        List<String> productIds = ordersCreateRequest.getOrderItems()
                .stream()
                .map(OrdersCreateItemRequest::getProductId)
                .distinct()
                .toList();
        ProductFilter productFilter = new ProductFilter();
        productFilter.setIds(productIds);

         List<ProductResponse> responseListProduct = productClient.search(productFilter);
         Map<String, ProductResponse> productResponseMap = new HashMap<>();
        for (ProductResponse productResponse : responseListProduct) {
            productResponseMap.put(productResponse.getId(), productResponse);
        }

         OrderEntity orderEntity = new OrderEntity();
         orderEntity.setCustomerId(ordersCreateRequest.getCustomerId());
         orderEntity.setStatus(OrderStatus.NEW.name());
         orderEntity.setPromotionId(ordersCreateRequest.getPromotionId());
         orderEntity.setTotalAmount(0);

         OrderEntity newOrder = orderRepository.save(orderEntity);
         List<OrderItemEntity> orderItemList = new ArrayList<>();
         List<StockProductItemRequest> listStockProductItemRequest = new ArrayList<>();
         Integer totalAmount = 0;

         for (OrdersCreateItemRequest orderItem : ordersCreateRequest.getOrderItems()) {
             listStockProductItemRequest.add(new StockProductItemRequest(orderItem.getProductId(), orderItem.getQuantity()));
             ProductResponse productResponse = productResponseMap.get(orderItem.getProductId());
             if (productResponse == null) {
                 throw new ApplicationException("Product not found");
             }

             if (productResponse.getStock() < orderItem.getQuantity()) {
                 throw new ApplicationException("Not enough stock");
             }

             OrderItemEntity newOrderItemRequest = new OrderItemEntity();
             newOrderItemRequest.setOrderId(newOrder.getId());
             newOrderItemRequest.setProductId(orderItem.getProductId());
             newOrderItemRequest.setPrice(productResponse.getPrice());
             newOrderItemRequest.setQuantity(orderItem.getQuantity());
             newOrderItemRequest.setOrder(newOrder);
             newOrderItemRequest.setIsDeleted(false);

             totalAmount +=  orderItem.getQuantity() * productResponse.getPrice();
             orderItemList.add(newOrderItemRequest);
         }

        if (orderEntity.getPromotionId() != null) {
            PromotionsFindId promotionsFindId = new PromotionsFindId();
            promotionsFindId.setId(orderEntity.getPromotionId());
            PromotionsResponse resPromotion = promotionsClient.findId(promotionsFindId);
            if (resPromotion == null || !resPromotion.getIsActive()) {
                throw ApplicationErrors.PROMOTION_NOT_EXISTS();
            }
            if (resPromotion.getEndAt().isBefore(Instant.now())  ) {
                throw ApplicationErrors.PROMOTION_Expired();
            }
            if (resPromotion.getStartAt().isAfter(Instant.now())) {
                throw ApplicationErrors.PROMOTION_UNISSUED();
            }
            if (resPromotion.getMinOrderAmount().compareTo(BigDecimal.valueOf(totalAmount)) > 0) {
                throw ApplicationErrors.INELIGIBLE_TOTAL_AMOUNT();
            }
            if (resPromotion.getUsageLimit() < resPromotion.getUsedCount()) {
                throw ApplicationErrors.USER_LIMIT_EXCEEDED();
            }

            if(DiscountType.FIXED.name().equals(resPromotion.getDiscountType())) {
                totalAmount -= resPromotion.getDiscountValue().intValue();
            } else {
                totalAmount -= (int) (totalAmount * (resPromotion.getDiscountValue().doubleValue() / 100.0));
            }

            promotionsClient.incrementUsedCount(orderEntity.getPromotionId());
        }

         newOrder.setTotalAmount(totalAmount);
         newOrder.setIsDeleted(false);
         newOrder.setOrderItemList(orderItemList);
         OrderEntity order = orderRepository.save(newOrder);
//         orderItemRepository.saveAll(orderItemList);
         productClient.stockProduct(new StockProductRequest(listStockProductItemRequest));
         return orderMapper.to(order);
    }



    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> findAll() {
        return orderMapper.List(orderRepository.findAll());
    }
}
