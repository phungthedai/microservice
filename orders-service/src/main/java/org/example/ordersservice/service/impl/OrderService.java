package org.example.ordersservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ordersservice.dto.request.OrdersCreateRequest;
import org.example.ordersservice.dto.response.OrderResponse;
import org.example.ordersservice.entity.OrderEntity;
import org.example.ordersservice.mapper.OrderMapper;
import org.example.ordersservice.repository.OrderRepository;
import org.example.ordersservice.service.IOrders;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements IOrders {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse create(OrdersCreateRequest ordersCreateRequest) {
        OrderEntity orderEntity = orderMapper.create(ordersCreateRequest);
        orderEntity.setTotalAmount(ordersCreateRequest.getTotalAmount());

        OrderEntity newOrder = orderRepository.save(orderEntity);
        return orderMapper.to(newOrder);
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderMapper.List(orderRepository.findAll());
    }
}
