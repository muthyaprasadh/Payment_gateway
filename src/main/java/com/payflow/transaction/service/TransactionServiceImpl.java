package com.payflow.transaction.service;

import com.payflow.auth.entity.User;
import com.payflow.auth.repository.UserRepository;
import com.payflow.transaction.dto.TransactionResponse;
import com.payflow.transaction.entity.Transaction;
import com.payflow.transaction.repository.TransactionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            UserRepository userRepository) {

        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TransactionResponse> getTransactionHistory() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<Transaction> transactions =
                transactionRepository
                        .findBySenderOrReceiverOrderByCreatedAtDesc(
                                user,
                                user
                        );

        return transactions.stream()
                .map(transaction -> TransactionResponse.builder()
                        .type(transaction.getTransactionType().name())
                        .amount(transaction.getAmount())
                        .sender(transaction.getSender().getEmail())
                        .receiver(transaction.getReceiver().getEmail())
                        .createdAt(transaction.getCreatedAt())
                        .build())
                .toList();
    }
}