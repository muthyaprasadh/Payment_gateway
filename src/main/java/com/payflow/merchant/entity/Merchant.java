package com.payflow.merchant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "merchants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String apiKey;

    @Column(nullable = false, unique = true)
    private String secretKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MerchantStatus status;
@Builder.Default
@Column(nullable = false)
private LocalDateTime createdAt = LocalDateTime.now();
}