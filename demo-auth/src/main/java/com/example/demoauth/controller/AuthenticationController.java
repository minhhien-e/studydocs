package com.example.demoauth.controller;

import com.example.demoauth.dto.*;
import com.example.demoauth.shared.web.ApiResponse;
import com.example.demoauth.service.AuthenticationService;
import com.example.demoauth.service.ForgotPasswordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/login/provider/{provider}")
    public ApiResponse<TokenResponseDto> loginProvider(
            @PathVariable String provider,
            @Valid @RequestBody LoginProviderRequestDto request) {

        return ApiResponse.success(authenticationService.loginWithProvider(provider, request));
    }

    @PostMapping("/login/local")
    public ApiResponse<TokenResponseDto> loginLocal(
            @Valid @RequestBody LoginLocalRequestDto request) {
        return ApiResponse.success(authenticationService.loginWithLocal(request));
    }

    @PostMapping("/register/local")
    public ApiResponse<TokenResponseDto> registerLocal(
            @Valid @RequestBody RegisterLocalRequestDto request) {
        return ApiResponse.success(authenticationService.registerLocal(request));
    }

    @PostMapping("/refresh")
    public ApiResponse<TokenResponseDto> refreshToken(
            @Valid @RequestBody RefreshTokenRequestDto request) {
        return ApiResponse.success(authenticationService.refreshToken(request));
    }

    @PostMapping("/forgot-password/request")
    public ApiResponse<Void> requestOtp(
            @Valid @RequestBody ForgotPasswordRequestDto request) {
        forgotPasswordService.generateAndSendOtp(request.getEmail());
        return ApiResponse.success(null);
    }

    @PostMapping("/forgot-password/confirm")
    public ApiResponse<Void> confirmOtp(
            @Valid @RequestBody ConfirmForgotPasswordRequestDto request) {
        forgotPasswordService.verifyAndResetPassword(request.getEmail(), request.getOtp(), request.getNewPassword());
        return ApiResponse.success(null);
    }
}