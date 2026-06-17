package org.example.ordersservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ordersservice.dto.request.OrdersCreateItemRequest;
import org.example.ordersservice.dto.request.OrdersCreateRequest;
import org.example.ordersservice.dto.response.OrderItemResponse;
import org.example.ordersservice.dto.response.OrderResponse;
import org.example.ordersservice.service.impl.OrderItemService;
import org.example.ordersservice.service.impl.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("orders-item")
public class OrderItemController {

    private final OrderItemService orderItemService;


    @PostMapping
    public ResponseEntity<OrderItemResponse> createCategory(@Valid @RequestBody OrdersCreateItemRequest ordersCreateItemRequest) {
        return ResponseEntity.ok(orderItemService.create(ordersCreateItemRequest));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<OrderItemResponse>> getAll() {
        return ResponseEntity.ok(orderItemService.findAll());
    }
}
