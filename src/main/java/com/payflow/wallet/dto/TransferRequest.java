package com.payflow.wallet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {

    @NotBlank(message = "Receiver email is required")
    @Email(message = "Invalid email")
    private String receiverEmail;

    @DecimalMin(value = "1.0", message = "Amount must be greater than zero")
    private BigDecimal amount;
}