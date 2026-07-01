package com.payflow.admin.service;

import com.payflow.admin.dto.AdminDashboardResponse;
import com.payflow.admin.dto.UserResponse;
import com.payflow.admin.dto.WalletAdminResponse;
import com.payflow.auth.entity.User;
import com.payflow.auth.repository.UserRepository;
import com.payflow.transaction.dto.TransactionResponse;
import com.payflow.transaction.entity.Transaction;
import com.payflow.transaction.repository.TransactionRepository;
import com.payflow.wallet.entity.Wallet;
import com.payflow.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public AdminServiceImpl(
            UserRepository userRepository,
            WalletRepository walletRepository,
            TransactionRepository transactionRepository) {

        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .role(user.getRole().name())
                        .createdAt(user.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    public List<WalletAdminResponse> getAllWallets() {

        return walletRepository.findAll()
                .stream()
                .map(wallet -> WalletAdminResponse.builder()
                        .walletId(wallet.getId())
                        .userEmail(wallet.getUser().getEmail())
                        .balance(wallet.getBalance())
                        .currency(wallet.getCurrency())
                        .build())
                .toList();
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {

        return transactionRepository.findAll()
                .stream()
                .map(transaction -> TransactionResponse.builder()
                        .type(transaction.getTransactionType().name())
                        .amount(transaction.getAmount())
                        .sender(transaction.getSender().getEmail())
                        .receiver(transaction.getReceiver().getEmail())
                        .createdAt(transaction.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    public AdminDashboardResponse getDashboard() {

        long totalUsers = userRepository.count();
        long totalWallets = walletRepository.count();
        long totalTransactions = transactionRepository.count();

        BigDecimal totalBalance = walletRepository.findAll()
                .stream()
                .map(Wallet::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return AdminDashboardResponse.builder()
                .totalUsers(totalUsers)
                .totalWallets(totalWallets)
                .totalTransactions(totalTransactions)
                .totalBalance(totalBalance)
                .build();
    }
}