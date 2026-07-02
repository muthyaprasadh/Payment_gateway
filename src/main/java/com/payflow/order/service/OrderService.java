package com.payflow.order.service;

import com.payflow.order.dto.CreateOrderRequest;
import com.payflow.order.dto.CreateOrderResponse;
import com.payflow.order.dto.PayOrderRequest;
import com.payflow.order.dto.PayOrderResponse;

public interface OrderService {

    CreateOrderResponse createOrder(
            CreateOrderRequest request,
            String merchantEmail);

            PayOrderResponse payOrder(
        java.util.UUID orderId,
        String customerEmail,
        PayOrderRequest request);

}