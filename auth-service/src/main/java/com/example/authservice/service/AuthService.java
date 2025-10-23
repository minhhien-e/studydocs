package com.example.authservice.service;

import com.example.authservice.model.dto.request.LoginRequest;
import com.example.authservice.model.dto.request.RegisterLocalRequest;
import com.example.authservice.model.dto.response.TokenResponse;
import com.example.authservice.model.dto.response.UserResponse;

public interface AuthService {
    /**
     * Đăng ký tài khoản local
     * 1. Kiểm tra email tồn tại
     * 2. Tạo user mới
     * 3. Tạo OAuth2 authorization
     */
    TokenResponse registerLocal(RegisterLocalRequest request);

    /**
     * Đăng nhập local
     * 1. Validate credentials
     * 2. Tạo OAuth2 authorization
     * 3. Trả về tokens
     */
    TokenResponse loginLocal(LoginRequest request);

    /**
     * Refresh token để lấy access token mới
     * Sử dụng OAuth2AuthorizationService của Spring
     */
    TokenResponse refreshToken(String refreshToken, String clientId);

    /**
     * Lấy thông tin user hiện tại
     */
    UserResponse getCurrentUser(String username);
}