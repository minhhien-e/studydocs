package com.example.authservice.exception;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Class định nghĩa format chuẩn cho response error
 * Được sử dụng để trả về thông tin lỗi cho client
 * Format JSON:
 * {
 *   "timestamp": "2024-03-15T10:30:00",
 *   "status": 401,
 *   "error": "Lỗi xác thực",
 *   "message": "Email hoặc mật khẩu không chính xác",
 *   "path": "/api/auth/login",
 *   "errorCode": "AUTH001"
 * }
 */
@Data
public class ErrorResponse {
    private LocalDateTime timestamp; // Thời điểm xảy ra lỗi
    private int status;             //etc.)
    private String message;         // Message mô tả lỗi HTTP status code (400, 401, 403, etc.)
    private String error;           // Loại lỗi (Lỗi xác thực, Lỗi phân quyền, 
    private String path;           // API endpoint gặp lỗi
    private AuthErrorCode errorCode;       // Mã lỗi để client có thể handle (optional)

    /**
     * Constructor cho trường hợp không có errorCode
     */
    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Constructor cho trường hợp có errorCode
     */
    public ErrorResponse(int status, String error, String message, String path, AuthErrorCode errorCode) {
        this(status, error, message, path);
        this.errorCode = errorCode;
    }
} 