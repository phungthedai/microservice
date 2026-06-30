package org.example.ordersservice.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.ordersservice.entity.OrderEntity;
import org.example.ordersservice.entity.OrderItemEntity;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderCreateEvent extends OrderEntity {
    private List<OrderItemEntity> orderItemList;
}
