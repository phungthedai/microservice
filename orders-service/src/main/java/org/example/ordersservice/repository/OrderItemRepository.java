package org.example.ordersservice.repository;

import org.example.ordersservice.dto.request.CreateOrderItem;
import org.example.ordersservice.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, String> {
}
