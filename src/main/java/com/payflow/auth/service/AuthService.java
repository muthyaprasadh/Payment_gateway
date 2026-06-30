package com.payflow.auth.service;

import com.payflow.auth.dto.LoginRequest;
import com.payflow.auth.dto.LoginResponse;
import com.payflow.auth.dto.RegisterRequest;
import com.payflow.auth.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    

}