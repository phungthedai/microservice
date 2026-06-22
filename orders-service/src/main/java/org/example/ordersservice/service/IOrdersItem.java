package org.example.ordersservice.service;


import org.example.ordersservice.dto.request.CreateOrderItem;
import org.example.ordersservice.dto.request.OrdersCreateItemRequest;
import org.example.ordersservice.dto.response.OrderItemResponse;

import java.util.List;

public interface IOrdersItem {
    OrderItemResponse create(CreateOrderItem createOrderItem);
    List<OrderItemResponse> findAll();
}
