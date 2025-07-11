package com.example.authservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * Class xử lý tập trung tất cả các exception trong ứng dụng
 * Đảm bảo format error response thống nhất
 * 
 * Cách hoạt động:
 * 1. Exception được throw từ bất kỳ đâu trong ứng dụng
 * 2. GlobalExceptionHandler bắt exception
 * 3. Tạo ErrorResponse với format chuẩn
 * 4. Trả về cho client
 */
@Slf4j  // Tự động tạo logger
@RestControllerAdvice  // Đánh dấu đây là class xử lý exception cho toàn bộ REST API
public class GlobalExceptionHandler {

    /**
     * Xử lý AuthenticationException
     * Ví dụ: Sai password, tài khoản bị khóa
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException ex, WebRequest request) {
        
        // Log lỗi để debug
        log.error("Lỗi xác thực: {} (Mã lỗi: {})", ex.getMessage(), ex.getErrorCode());
        
        // Tạo response chuẩn
        ErrorResponse error = new ErrorResponse(
                ex.getHttpStatus().value(),
                "Lỗi xác thực",
                ex.getMessage(),
                getRequestPath(request),
                ex.getErrorCode()  // KHÔNG cần tạo mới AuthenticationException nữa
        );
        
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    /**
     * Xử lý AuthorizationException
     * Ví dụ: Không có quyền truy cập, role không hợp lệ
     */
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationException(
            AuthorizationException ex, WebRequest request) {
        
        log.error("Lỗi phân quyền: {} (Mã lỗi: {})", ex.getMessage(), ex.getErrorCode());
        
        ErrorResponse error = new ErrorResponse(
            ex.getHttpStatus().value(),
            "Lỗi phân quyền",
            ex.getMessage(),
            getRequestPath(request),
            ex.getErrorCode()
        );
        
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    /**
     * Xử lý ValidationException
     * Ví dụ: Form data không hợp lệ
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            ValidationException ex, WebRequest request) {
        
        log.error("Lỗi validation: {}", ex.getMessage());
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Lỗi validation",
            ex.getMessage(),
            getRequestPath(request),
            ex.getErrorCode()
        );
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Xử lý tất cả các exception khác không được handle cụ thể
     * Đây là catch-all cuối cùng để đảm bảo luôn trả về error format chuẩn
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        
        // Log stack trace để debug
        log.error("Lỗi không xác định: ", ex);
        
        // Trả về thông tin lỗi chung chung, không expose chi tiết lỗi
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Lỗi hệ thống",
            "Có lỗi xảy ra, vui lòng thử lại sau",
            getRequestPath(request)
        );
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Helper method để lấy đường dẫn của request gặp lỗi
     */
    private String getRequestPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
} 