package com.payflow.auth.service;

import com.payflow.auth.dto.LoginRequest;
import com.payflow.auth.dto.LoginResponse;
import com.payflow.auth.dto.RegisterRequest;
import com.payflow.auth.dto.RegisterResponse;
import com.payflow.auth.entity.Role;
import com.payflow.auth.entity.User;
import com.payflow.auth.repository.UserRepository;
import com.payflow.exception.EmailAlreadyExistsException;
import com.payflow.exception.InvalidCredentialsException;
import com.payflow.security.JwtService;
import com.payflow.wallet.entity.Wallet;
import com.payflow.wallet.repository.WalletRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final WalletRepository walletRepository;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            WalletRepository walletRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.walletRepository = walletRepository;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        Wallet wallet = Wallet.builder()
                .user(savedUser)
                .balance(BigDecimal.ZERO)
                .currency("INR")
                .createdAt(LocalDateTime.now())
                .build();

        walletRepository.save(wallet);

        return new RegisterResponse("User registered successfully");
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}