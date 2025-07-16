package com.example.authservice.controller;

import com.example.authservice.model.dto.request.LoginRequest;
import com.example.authservice.model.dto.request.RegisterRequest;
import com.example.authservice.model.dto.request.SocialLoginRequest;
import com.example.authservice.model.dto.response.TokenResponse;
import com.example.authservice.model.dto.response.UserResponse;
import com.example.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "API xác thực người dùng")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Đăng ký tài khoản mới")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse userResponse = authService.registerLocal(request);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Đăng nhập")
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "Đăng nhập bằng tài khoản social")
    @PostMapping("/social/login")
    public ResponseEntity<TokenResponse> socialLogin(@Valid @RequestBody SocialLoginRequest request) {
        return ResponseEntity.ok(authService.socialLogin(request));
    }

    @Operation(summary = "Làm mới token")
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestParam String refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @Operation(summary = "Xác thực email")
    @GetMapping("/verify-email")
    public ResponseEntity<Boolean> verifyEmail(@RequestParam String token) {
        return ResponseEntity.ok(authService.verifyEmail(token));
    }

    @Operation(summary = "Kiểm tra email tồn tại")
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(authService.isEmailExists(email));
    }

    @Operation(summary = "Đăng xuất")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Lấy thông tin người dùng hiện tại")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }
} 