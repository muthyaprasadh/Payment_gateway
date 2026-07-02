package com.payflow.order.service;

import com.payflow.merchant.entity.Merchant;
import com.payflow.merchant.repository.MerchantRepository;
import com.payflow.order.dto.CreateOrderRequest;
import com.payflow.order.dto.CreateOrderResponse;
import com.payflow.order.dto.PayOrderRequest;
import com.payflow.order.dto.PayOrderResponse;
import com.payflow.order.entity.OrderStatus;
import com.payflow.order.entity.PaymentOrder;
import com.payflow.order.repository.PaymentOrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final MerchantRepository merchantRepository;

    public OrderServiceImpl(
            PaymentOrderRepository paymentOrderRepository,
            MerchantRepository merchantRepository) {

        this.paymentOrderRepository = paymentOrderRepository;
        this.merchantRepository = merchantRepository;
    }

    @Override
    public CreateOrderResponse createOrder(
            CreateOrderRequest request,
            String merchantEmail) {

        Merchant merchant = merchantRepository.findByEmail(merchantEmail)
                .orElseThrow(() ->
                        new RuntimeException("Merchant not found"));

        PaymentOrder order = PaymentOrder.builder()
                .merchant(merchant)
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .receipt(request.getReceipt())
                .status(OrderStatus.CREATED)
                .build();

        paymentOrderRepository.save(order);

        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .amount(order.getAmount())
                .currency(order.getCurrency())
                .receipt(order.getReceipt())
                .status(order.getStatus().name())
                .build();
    }

    @Override
    public PayOrderResponse payOrder(
            UUID orderId,
            String customerEmail,
            PayOrderRequest request) {

        // Temporary implementation.
        // We'll replace this with the complete payment flow next.

        return PayOrderResponse.builder()
                .message("Payment API coming next")
                .orderStatus("CREATED")
                .customerBalance(BigDecimal.ZERO)
                .merchantBalance(BigDecimal.ZERO)
                .build();
    }
}