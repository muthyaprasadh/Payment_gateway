package com.payflow.merchant.service;

import com.payflow.merchant.dto.MerchantLoginRequest;
import com.payflow.merchant.dto.MerchantLoginResponse;
import com.payflow.merchant.dto.MerchantRegisterRequest;
import com.payflow.merchant.dto.MerchantRegisterResponse;
import com.payflow.merchant.entity.Merchant;
import com.payflow.merchant.entity.MerchantStatus;
import com.payflow.merchant.repository.MerchantRepository;
import com.payflow.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public MerchantServiceImpl(
            MerchantRepository merchantRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.merchantRepository = merchantRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public MerchantRegisterResponse registerMerchant(
            MerchantRegisterRequest request) {

        if (merchantRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Merchant already exists");
        }

        String apiKey = "pk_live_" +
                UUID.randomUUID().toString().replace("-", "");

        String secretKey = "sk_live_" +
                UUID.randomUUID().toString().replace("-", "");

        Merchant merchant = Merchant.builder()
                .businessName(request.getBusinessName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .apiKey(apiKey)
                .secretKey(secretKey)
                .status(MerchantStatus.ACTIVE)
                .build();

        merchantRepository.save(merchant);

        return MerchantRegisterResponse.builder()
                .message("Merchant registered successfully")
                .businessName(merchant.getBusinessName())
                .email(merchant.getEmail())
                .apiKey(merchant.getApiKey())
                .secretKey(merchant.getSecretKey())
                .build();
    }

    @Override
    public MerchantLoginResponse loginMerchant(
            MerchantLoginRequest request) {

        Merchant merchant = merchantRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Merchant not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                merchant.getPassword())) {

            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(merchant.getEmail());

        return MerchantLoginResponse.builder()
                .token(token)
                .businessName(merchant.getBusinessName())
                .apiKey(merchant.getApiKey())
                .message("Merchant login successful")
                .build();
    }
}