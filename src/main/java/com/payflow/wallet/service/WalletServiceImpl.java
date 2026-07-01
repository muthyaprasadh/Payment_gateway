package com.payflow.wallet.service;

import com.payflow.auth.entity.User;
import com.payflow.auth.repository.UserRepository;
import com.payflow.wallet.dto.DepositRequest;
import com.payflow.wallet.dto.DepositResponse;
import com.payflow.wallet.dto.TransferRequest;
import com.payflow.wallet.dto.TransferResponse;
import com.payflow.wallet.dto.WalletResponse;
import com.payflow.wallet.dto.WithdrawRequest;
import com.payflow.wallet.dto.WithdrawResponse;
import com.payflow.wallet.entity.Wallet;
import com.payflow.exception.InsufficientBalanceException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.payflow.wallet.repository.WalletRepository;
import java.math.BigDecimal;

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
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        return new WalletResponse(
                wallet.getBalance(),
                wallet.getCurrency()
        );
    }

    @Override
    public DepositResponse deposit(DepositRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setBalance(
                wallet.getBalance().add(request.getAmount()));

        walletRepository.save(wallet);

        return new DepositResponse(
                "Amount deposited successfully",
                wallet.getBalance()
        );
    }

    @Override
    public WithdrawResponse withdraw(WithdrawRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        wallet.setBalance(
                wallet.getBalance().subtract(request.getAmount()));

        walletRepository.save(wallet);

        return new WithdrawResponse(
                "Amount withdrawn successfully",
                wallet.getBalance()
        );
    }

   @Override
   @Transactional
public TransferResponse transfer(TransferRequest request) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String senderEmail = authentication.getName();

    User sender = userRepository.findByEmail(senderEmail)
            .orElseThrow(() ->
                    new RuntimeException("Sender not found"));

    User receiver = userRepository.findByEmail(request.getReceiverEmail())
            .orElseThrow(() ->
                    new RuntimeException("Receiver not found"));

    if (sender.getEmail().equalsIgnoreCase(receiver.getEmail())) {
        throw new RuntimeException("Cannot transfer to yourself");
    }

    Wallet senderWallet = walletRepository.findByUser(sender)
            .orElseThrow(() ->
                    new RuntimeException("Sender wallet not found"));

    Wallet receiverWallet = walletRepository.findByUser(receiver)
            .orElseThrow(() ->
                    new RuntimeException("Receiver wallet not found"));

    if (senderWallet.getBalance().compareTo(request.getAmount()) < 0) {
        throw new InsufficientBalanceException("Insufficient balance");
    }

    senderWallet.setBalance(
            senderWallet.getBalance().subtract(request.getAmount()));

    receiverWallet.setBalance(
            receiverWallet.getBalance().add(request.getAmount()));

    walletRepository.save(senderWallet);
    walletRepository.save(receiverWallet);

    return new TransferResponse(
            "Transfer successful",
            senderWallet.getBalance(),
            receiverWallet.getBalance()
    );
}
}