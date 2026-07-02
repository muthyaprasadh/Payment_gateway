package com.payflow.merchantwallet.service;

import com.payflow.merchantwallet.dto.MerchantWalletResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MerchantWalletServiceImpl implements MerchantWalletService {

    @Override
    public MerchantWalletResponse getWallet() {

        return MerchantWalletResponse.builder()
                .balance(BigDecimal.ZERO)
                .currency("INR")
                .build();
    }
}