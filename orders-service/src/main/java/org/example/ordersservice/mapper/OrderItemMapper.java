package org.example.ordersservice.mapper;

import org.example.ordersservice.dto.request.CreateOrderItem;
import org.example.ordersservice.dto.request.OrdersCreateItemRequest;
import org.example.ordersservice.dto.response.OrderItemResponse;
import org.example.ordersservice.entity.OrderItemEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemEntity create(CreateOrderItem CreateOrderItem);
    OrderItemResponse to(OrderItemEntity orderItemEntity);
    List<OrderItemResponse> List(List<OrderItemEntity> orderItemEntity);
    List<OrderItemEntity> toListOrderItem(List<CreateOrderItem> createOrderItem);
}
