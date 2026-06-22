package org.example.ordersservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ordersservice.dto.request.CreateOrderItem;
import org.example.ordersservice.dto.request.OrdersCreateItemRequest;
import org.example.ordersservice.dto.response.OrderItemResponse;
import org.example.ordersservice.entity.OrderItemEntity;
import org.example.ordersservice.exception.ApplicationErrors;
import org.example.ordersservice.exception.ApplicationException;
import org.example.ordersservice.mapper.OrderItemMapper;
import org.example.ordersservice.repository.OrderItemRepository;
import org.example.ordersservice.repository.OrderRepository;
import org.example.ordersservice.service.IOrdersItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemService implements IOrdersItem {

    private final OrderItemRepository  orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public OrderItemResponse create(CreateOrderItem createOrderItem) {
//        var order = orderRepository.findById(ordersCreateItemRequest.getOrderId());
//        if (order.isEmpty()) {
//            throw ApplicationErrors.ORDER_NOT_EXISTS();
//        }
        OrderItemEntity newOrder = orderItemRepository.save(orderItemMapper.create(createOrderItem));
        return orderItemMapper.to(newOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponse> findAll() {
        return orderItemMapper.List(orderItemRepository.findAll());
    }
}
