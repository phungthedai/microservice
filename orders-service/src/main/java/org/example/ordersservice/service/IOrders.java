package org.example.ordersservice.service;


import org.example.ordersservice.dto.request.OrdersCreateRequest;
import org.example.ordersservice.dto.response.OrderResponse;

import java.util.List;

public interface IOrders {
    OrderResponse create(OrdersCreateRequest ordersCreateRequest);
    List<OrderResponse> findAll();
}
