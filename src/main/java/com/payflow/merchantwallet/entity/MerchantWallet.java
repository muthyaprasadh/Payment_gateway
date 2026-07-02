package com.payflow.merchantwallet.entity;

import com.payflow.merchant.entity.Merchant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "merchant_wallets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "merchant_id", nullable = false, unique = true)
    private Merchant merchant;

    @Builder.Default
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Builder.Default
    @Column(nullable = false)
    private String currency = "INR";

    @Default
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}