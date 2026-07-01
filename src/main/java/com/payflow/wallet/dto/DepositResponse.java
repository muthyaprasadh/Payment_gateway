package com.payflow.wallet.dto;

import java.math.BigDecimal;

public class DepositResponse {

    private String message;
    private BigDecimal balance;

    public DepositResponse() {
    }

    public DepositResponse(String message, BigDecimal balance) {
        this.message = message;
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}