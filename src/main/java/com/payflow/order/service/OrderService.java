package com.payflow.order.service;

import com.payflow.order.dto.CreateOrderRequest;
import com.payflow.order.dto.CreateOrderResponse;

public interface OrderService {

    CreateOrderResponse createOrder(
            CreateOrderRequest request,
            String merchantEmail);

}