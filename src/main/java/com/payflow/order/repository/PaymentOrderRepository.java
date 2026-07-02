package com.payflow.order.repository;

import com.payflow.order.entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentOrderRepository
        extends JpaRepository<PaymentOrder, UUID> {
}