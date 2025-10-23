package com.example.authservice.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception xử lý các lỗi liên quan đến phân quyền
 * Ví dụ:
 * - Không có quyền truy cập resource
 * - Role không tồn tại
 * - Không đủ quyền thực hiện hành động
 * 
 * Khác với AuthenticationException:
 * - AuthenticationException: Lỗi xác thực danh tính (ai?)
 * - AuthorizationException: Lỗi phân quyền (được phép làm gì?)
 */
public class AuthorizationException extends BaseException {
    /**
     * Constructor với AuthErrorCode
     * Luôn trả về HTTP 403 Forbidden
     * @param errorCode Mã lỗi từ AuthErrorCode
     */
    public AuthorizationException(AuthErrorCode errorCode) {
        super(errorCode.getDefaultMessage(), errorCode, HttpStatus.FORBIDDEN);
    }
} 