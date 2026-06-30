package com.payflow.auth.controller;

import com.payflow.auth.dto.RegisterRequest;
import com.payflow.auth.dto.RegisterResponse;
import com.payflow.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request) {

        return authService.register(request);

    }

}