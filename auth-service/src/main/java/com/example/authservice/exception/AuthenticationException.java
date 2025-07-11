package com.example.authservice.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception xử lý các lỗi liên quan đến xác thực người dùng
 * Ví dụ:
 * - Sai thông tin đăng nhập (email/password)
 * - Tài khoản bị khóa
 * - Token không hợp lệ/hết hạn
 */
public class AuthenticationException extends BaseException {
    /**
     * Constructor với AuthErrorCode
     * Sử dụng message mặc định từ AuthErrorCode
     * @param errorCode Mã lỗi từ AuthErrorCode
     */
    public AuthenticationException(AuthErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getCode(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Constructor với message tùy chỉnh
     * Cho phép thay đổi message nhưng vẫn giữ mã lỗi
     * @param errorCode     Mã lỗi từ AuthErrorCode
     * @param customMessage Message tùy chỉnh thay vì dùng message mặc định
     */
    public AuthenticationException(AuthErrorCode errorCode, String customMessage) {
        super(customMessage, errorCode.getCode(), HttpStatus.UNAUTHORIZED);
    }
} 