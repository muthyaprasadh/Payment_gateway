package com.payflow.wallet.controller;

import com.payflow.wallet.dto.DepositRequest;
import com.payflow.wallet.dto.DepositResponse;
import com.payflow.wallet.dto.WalletResponse;
import com.payflow.wallet.dto.WithdrawRequest;
import com.payflow.wallet.dto.WithdrawResponse;
import com.payflow.wallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.payflow.wallet.dto.TransferRequest;
import com.payflow.wallet.dto.TransferResponse;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public WalletResponse getWallet() {
        return walletService.getWallet();
    }

    @PostMapping("/deposit")
    public DepositResponse deposit(
            @Valid @RequestBody DepositRequest request) {

        return walletService.deposit(request);
    }
    @PostMapping("/transfer")
public TransferResponse transfer(
        @Valid @RequestBody TransferRequest request) {

    return walletService.transfer(request);
}

    @PostMapping("/withdraw")
    public WithdrawResponse withdraw(
            @Valid @RequestBody WithdrawRequest request) {

        return walletService.withdraw(request);
    }
}