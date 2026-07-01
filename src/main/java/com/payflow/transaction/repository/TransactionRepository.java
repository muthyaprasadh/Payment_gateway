package com.payflow.transaction.repository;

import com.payflow.auth.entity.User;
import com.payflow.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository
        extends JpaRepository<Transaction, UUID> {

    List<Transaction> findBySenderOrReceiverOrderByCreatedAtDesc(
            User sender,
            User receiver
    );
}