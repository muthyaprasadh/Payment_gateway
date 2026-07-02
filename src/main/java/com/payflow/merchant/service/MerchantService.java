package com.payflow.merchant.service;

import com.payflow.merchant.dto.MerchantLoginRequest;
import com.payflow.merchant.dto.MerchantLoginResponse;
import com.payflow.merchant.dto.MerchantRegisterRequest;
import com.payflow.merchant.dto.MerchantRegisterResponse;

public interface MerchantService {

    MerchantLoginResponse loginMerchant(
        MerchantLoginRequest request);

    MerchantRegisterResponse registerMerchant(
            MerchantRegisterRequest request);

}