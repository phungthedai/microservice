package org.example.ordersservice.mapper;

import org.example.ordersservice.dto.request.OrdersCreateRequest;
import org.example.ordersservice.dto.response.OrderResponse;
import org.example.ordersservice.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity create(OrdersCreateRequest ordersCreateRequest);
    OrderResponse to(OrderEntity orderEntity);
    List<OrderResponse> List(List<OrderEntity> orderEntity);
}
