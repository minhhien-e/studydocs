package com.example.authservicev2.service.interfaces;

import com.example.authservicev2.domain.model.request.LocalRegisterRequest;
import com.example.authservicev2.domain.model.request.LocalLoginRequest;
import com.example.authservicev2.domain.model.request.LoginRequest;
import com.example.authservicev2.domain.model.request.RegisterRequest;
import com.example.authservicev2.domain.model.response.TokenResponse;
import com.example.authservicev2.domain.model.response.UserResponse;


public interface AuthService {
    UserResponse registerLocal(LocalRegisterRequest registerRequest);
    UserResponse registerSocial(RegisterRequest registerRequest);
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse loginLocal(LocalLoginRequest request);
    TokenResponse refreshToken(TokenResponse tokenResponse);
}
