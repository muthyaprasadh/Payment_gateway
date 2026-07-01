package com.payflow.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferResponse {

    private String message;
    private BigDecimal senderBalance;
    private BigDecimal receiverBalance;
}