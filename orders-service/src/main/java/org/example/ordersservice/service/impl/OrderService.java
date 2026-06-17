package org.example.ordersservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ordersservice.client.dto.request.ProductFilter;
import org.example.ordersservice.client.dto.response.ProductResponse;
import org.example.ordersservice.client.service.impl.ProductClientImpl;
import org.example.ordersservice.dto.request.OrdersCreateItemRequest;
import org.example.ordersservice.dto.request.OrdersCreateRequest;
import org.example.ordersservice.dto.response.OrderResponse;
import org.example.ordersservice.entity.OrderEntity;
import org.example.ordersservice.exception.ApplicationErrors;
import org.example.ordersservice.exception.ApplicationException;
import org.example.ordersservice.mapper.OrderMapper;
import org.example.ordersservice.repository.OrderRepository;
import org.example.ordersservice.service.IOrders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements IOrders {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductClientImpl productClient;

    @Override
    public OrderResponse create(OrdersCreateRequest ordersCreateRequest) {
        List<String> productIds = ordersCreateRequest.getOrderItems()
                .stream()
                .map(OrdersCreateItemRequest::getProductId)
                .distinct()
                .toList();
        ProductFilter productFilter = new ProductFilter();
        productFilter.setIds(productIds);

         List<ProductResponse> responseList = productClient.search(productFilter);
         if (responseList.isEmpty()) {
             throw new  ApplicationException(4001, 400, "nt");
         }

         return null;
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderMapper.List(orderRepository.findAll());
    }
}
