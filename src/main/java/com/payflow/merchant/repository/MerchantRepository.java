package com.payflow.merchant.repository;

import com.payflow.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantRepository
        extends JpaRepository<Merchant, UUID> {

    Optional<Merchant> findByEmail(String email);

    Optional<Merchant> findByApiKey(String apiKey);
}