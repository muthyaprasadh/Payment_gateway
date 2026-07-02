package com.payflow.merchantwallet.controller;

import com.payflow.merchantwallet.dto.MerchantWalletResponse;
import com.payflow.merchantwallet.service.MerchantWalletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchant/wallet")
public class MerchantWalletController {

    private final MerchantWalletService merchantWalletService;

    public MerchantWalletController(
            MerchantWalletService merchantWalletService) {

        this.merchantWalletService = merchantWalletService;
    }

    @GetMapping
    public MerchantWalletResponse getWallet() {
        return merchantWalletService.getWallet();
    }
}