package org.example.ordersservice.mapper;

import org.example.ordersservice.dto.request.CreateOrderItem;
import org.example.ordersservice.dto.request.OrdersCreateRequest;
import org.example.ordersservice.dto.response.OrderResponse;
import org.example.ordersservice.entity.OrderEntity;
import org.example.ordersservice.entity.OrderItemEntity;
import org.example.ordersservice.events.OrderCreateEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    OrderEntity create(OrdersCreateRequest ordersCreateRequest);
    OrderResponse to(OrderEntity orderEntity);
    List<OrderResponse> List(List<OrderEntity> orderEntity);
    OrderCreateEvent  toEvent(OrderEntity orderEntity);
}
