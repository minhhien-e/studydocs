package com.example.demoauth.controller;

import com.example.demoauth.dto.OtpResponseDto;
import com.example.demoauth.service.ForgotPasswordService;
import com.example.demoauth.shared.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal/auth")
@RequiredArgsConstructor
public class InterNalController {

    private final ForgotPasswordService forgotPasswordService;

    // Notification Service gọi API này để lấy mã OTP gửi mail
    @GetMapping("/get-otp")
    public ApiResponse<OtpResponseDto> getOtpForNotification(@RequestParam String email) {
        OtpResponseDto otp = forgotPasswordService.getOTP(email);
        return ApiResponse.success(otp);
    }
}