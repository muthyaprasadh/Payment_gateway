package com.payflow.merchant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRegisterResponse {

    private String message;

    private String businessName;

    private String email;

    private String apiKey;

    private String secretKey;
}