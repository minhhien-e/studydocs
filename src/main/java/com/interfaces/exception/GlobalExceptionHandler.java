package com.interfaces.exception;

import com.error.exception.DomainException;
import com.error.exception.InfrastructureException;
import com.interfaces.controller.UserController;
import com.interfaces.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Bắt tất cả DomainException
     * Chỉ trả về mã lỗi từ ErrorCode
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @ExceptionHandler(DomainException.class)
    public ApiResponse<?> handleDomainException(DomainException ex) {
        LOG.error(ex.getMethodName());
        return ApiResponse.error(
                ex.getErrorCode().getStatus().value(),
                ex.getErrorCode().getCode(),
                null // message không trả về
        );
    }
    @ExceptionHandler(InfrastructureException.class)
    public ApiResponse<?> handleInfrastructureException(InfrastructureException ex) {
        LOG.error("InfrastructureException method: {}", ex.getMethod());
        return ApiResponse.error(
                ex.getErrorCode().getStatus().value(),
                ex.getErrorCode().getCode(),
                null
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidationException(MethodArgumentNotValidException ex) {
        // Lấy field đầu tiên bị lỗi (hoặc bạn có thể gom tất cả)
        String field = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getField()
                : "unknown";
        LOG.error("Validation failed for field: {}", field);

        return ApiResponse.error(
                400,
                "U003", // INVALID_USER_INPUT
                null    // không trả message ra client
        );
    }

    /**
     * Bắt tất cả lỗi khác (unhandled)
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGeneralException(Exception ex) {
        return ApiResponse.error(
                500,
                "E000", // INTERNAL_SERVER_ERROR
                null
        );
    }
}
