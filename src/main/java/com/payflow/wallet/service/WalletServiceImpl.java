package com.payflow.wallet.service;

import com.payflow.auth.entity.User;
import com.payflow.auth.repository.UserRepository;
import com.payflow.wallet.dto.WalletResponse;
import com.payflow.wallet.entity.Wallet;
import com.payflow.wallet.repository.WalletRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletServiceImpl(
            WalletRepository walletRepository,
            UserRepository userRepository) {

        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WalletResponse getWallet() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Wallet not found"));

        return WalletResponse.builder()
                .balance(wallet.getBalance())
                .currency(wallet.getCurrency())
                .build();
    }
}