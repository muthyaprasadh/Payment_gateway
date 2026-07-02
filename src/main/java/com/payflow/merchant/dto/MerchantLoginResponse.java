package com.payflow.merchant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantLoginResponse {

    private String token;

    private String businessName;

    private String apiKey;

    private String message;
}