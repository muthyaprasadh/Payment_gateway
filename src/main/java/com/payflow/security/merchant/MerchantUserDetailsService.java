package com.payflow.security.merchant;

import com.payflow.merchant.entity.Merchant;
import com.payflow.merchant.repository.MerchantRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MerchantUserDetailsService implements UserDetailsService {

    private final MerchantRepository merchantRepository;

    public MerchantUserDetailsService(
            MerchantRepository merchantRepository) {

        this.merchantRepository = merchantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Merchant merchant = merchantRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Merchant not found"));

        return new MerchantUserDetails(merchant);
    }
}