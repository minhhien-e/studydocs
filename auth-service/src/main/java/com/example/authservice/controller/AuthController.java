package com.example.authservice.controller;

import com.example.authservice.model.dto.request.LoginRequest;
import com.example.authservice.model.dto.request.RegisterLocalRequest;
import com.example.authservice.model.dto.response.TokenResponse;
import com.example.authservice.model.dto.response.UserResponse;
import com.example.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for authentication and user management")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Đăng ký tài khoản local")
    @PostMapping("/register")
    public ResponseEntity<TokenResponse> registerLocal(@Valid @RequestBody RegisterLocalRequest request) {
        log.info("Processing local registration for user: {}", request.getEmail());
        return ResponseEntity.ok(authService.registerLocal(request));
    }

    @Operation(summary = "Đăng nhập với tài khoản local")
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loginLocal(@Valid @RequestBody LoginRequest request) {
        log.info("Processing local login for user: {}", request.getUsername());
        return ResponseEntity.ok(authService.loginLocal(request));
    }

    @Operation(summary = "Refresh token")
    @PostMapping("/token/refresh")
    public ResponseEntity<TokenResponse> refreshToken(
            @RequestParam("refresh_token") String refreshToken,
            @RequestParam("client_id") String clientId) {
        log.info("Processing token refresh for client: {}", clientId);
        return ResponseEntity.ok(authService.refreshToken(refreshToken, clientId));
    }

    @Operation(summary = "Lấy thông tin user hiện tại")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal OAuth2User principal) {
        log.info("Fetching user info for: {}", principal.getName());
        return ResponseEntity.ok(authService.getCurrentUser(principal.getName()));
    }
}