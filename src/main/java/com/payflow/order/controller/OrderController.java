package com.payflow.order.controller;

import com.payflow.order.dto.CreateOrderRequest;
import com.payflow.order.dto.CreateOrderResponse;
import com.payflow.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public CreateOrderResponse createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            Authentication authentication) {

        return orderService.createOrder(
                request,
                authentication.getName());
    }
}