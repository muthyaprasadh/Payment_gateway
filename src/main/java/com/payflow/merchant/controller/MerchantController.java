package com.payflow.merchant.controller;

import com.payflow.merchant.dto.MerchantLoginRequest;
import com.payflow.merchant.dto.MerchantLoginResponse;
import com.payflow.merchant.dto.MerchantRegisterRequest;
import com.payflow.merchant.dto.MerchantRegisterResponse;
import com.payflow.merchant.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/register")
    public MerchantRegisterResponse registerMerchant(
            @Valid @RequestBody MerchantRegisterRequest request) {

        return merchantService.registerMerchant(request);
    }

    @PostMapping("/login")
    public MerchantLoginResponse loginMerchant(
            @Valid @RequestBody MerchantLoginRequest request) {

        return merchantService.loginMerchant(request);
    }
}