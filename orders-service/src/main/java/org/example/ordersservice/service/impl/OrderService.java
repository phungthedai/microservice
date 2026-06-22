package org.example.ordersservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ordersservice.client.dto.request.ProductFilter;
import org.example.ordersservice.client.dto.request.PromotionsFindId;
import org.example.ordersservice.client.dto.response.ProductResponse;
import org.example.ordersservice.client.dto.response.PromotionsResponse;
import org.example.ordersservice.client.service.PromotionsClient;
import org.example.ordersservice.client.service.impl.ProductClientImpl;
import org.example.ordersservice.dto.request.CreateOrderItem;
import org.example.ordersservice.dto.request.OrdersCreateItemRequest;
import org.example.ordersservice.dto.request.OrdersCreateRequest;
import org.example.ordersservice.dto.response.OrderResponse;
import org.example.ordersservice.entity.OrderEntity;
import org.example.ordersservice.exception.ApplicationErrors;
import org.example.ordersservice.mapper.OrderItemMapper;
import org.example.ordersservice.mapper.OrderMapper;
import org.example.ordersservice.repository.OrderItemRepository;
import org.example.ordersservice.repository.OrderRepository;
import org.example.ordersservice.service.IOrders;
import org.example.ordersservice.service.IOrdersItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    public OrderResponse create(OrdersCreateRequest ordersCreateRequest) {
        List<String> productIds = ordersCreateRequest.getOrderItems()
                .stream()
                .map(OrdersCreateItemRequest::getProductId)
                .distinct()
                .toList();
        ProductFilter productFilter = new ProductFilter();
        productFilter.setIds(productIds);

         List<ProductResponse> responseListProduct = productClient.search(productFilter);
         int totalPrice = responseListProduct.stream()
                .mapToInt(ProductResponse::getPrice)
                .sum();

         OrderEntity orderEntity = new OrderEntity();
         orderEntity.setTotalAmount(totalPrice);

         if (orderEntity.getPromotionId() != null) {
             PromotionsFindId promotionsFindId = new PromotionsFindId();
             promotionsFindId.setId(orderEntity.getPromotionId());
             PromotionsResponse resPromotion = promotionsClient.findId(promotionsFindId);
             if (resPromotion == null || resPromotion.getIsActive()) {
                 throw ApplicationErrors.PROMOTION_NOT_EXISTS();
             }
             if (resPromotion.getEndAt().isBefore(Instant.now())  ) {
                 throw ApplicationErrors.PROMOTION_Expired();
             }
             if (resPromotion.getStartAt().isBefore(Instant.now())  ) {
                 throw ApplicationErrors.PROMOTION_UNISSUED();
             }
             if (resPromotion.getMinOrderAmount().compareTo(BigDecimal.valueOf(totalPrice)) > 0) {
                 throw ApplicationErrors.INELIGIBLE_TOTAL_AMOUNT();
             }
             if (resPromotion.getUsageLimit() > resPromotion.getUsedCount()) {
                 throw ApplicationErrors.USER_LIMIT_EXCEEDED();
             }
         }

         OrderEntity newOrder = orderRepository.save(orderEntity);

         List<CreateOrderItem> orderItemList = new ArrayList<>();
         for (OrdersCreateItemRequest orderItem : ordersCreateRequest.getOrderItems()) {
             CreateOrderItem newOrderItemRequest = new CreateOrderItem();
             newOrderItemRequest.setOrderId(newOrder.getId());
             newOrderItemRequest.setProductId(orderItem.getProductId());
             newOrderItemRequest.setPrice(responseListProduct.stream().filter(product -> product.getId().equals(orderItem.getProductId())).findFirst().get().getPrice());
             newOrderItemRequest.setQuantity(orderItem.getQuantity());
             orderItemList.add(newOrderItemRequest);
         }
         orderItemRepository.saveAll(orderItemList);

         promotionsClient.incrementUsedCount(orderEntity.getPromotionId());

         return orderMapper.to(newOrder);
    }



    @Override
    public List<OrderResponse> findAll() {
        return orderMapper.List(orderRepository.findAll());
    }
}
