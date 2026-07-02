package com.payflow.security.merchant;

import com.payflow.merchant.entity.Merchant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MerchantUserDetails implements UserDetails {

    private final Merchant merchant;

    public MerchantUserDetails(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_MERCHANT"));
    }

    @Override
    public String getPassword() {
        return merchant.getPassword();
    }

    @Override
    public String getUsername() {
        return merchant.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return merchant.getStatus().name().equals("ACTIVE");
    }
}