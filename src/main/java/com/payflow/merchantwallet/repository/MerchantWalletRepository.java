package com.payflow.merchantwallet.repository;

import com.payflow.merchant.entity.Merchant;
import com.payflow.merchantwallet.entity.MerchantWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantWalletRepository
        extends JpaRepository<MerchantWallet, UUID> {

    Optional<MerchantWallet> findByMerchant(Merchant merchant);
}