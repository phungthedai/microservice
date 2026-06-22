package org.example.ordersservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ordersservice.dto.request.OrdersCreateRequest;
import org.example.ordersservice.dto.response.OrderResponse;
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
@RequestMapping("v1/orders")
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid OrdersCreateRequest ordersCreateRequest) {
        return ResponseEntity.ok(orderService.create(ordersCreateRequest));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<OrderResponse>> getAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
