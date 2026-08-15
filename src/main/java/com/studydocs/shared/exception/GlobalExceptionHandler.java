package com.studydocs.shared.exception;

import com.studydocs.shared.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException exception, HttpServletRequest request) {
        ErrorCode errorCode = exception.getErrorCode();
        String traceId = getTraceId(request);

        log.warn("AppException [traceId: {}]: {}", traceId, exception.getMessage());

        ApiResponse<Object> apiResponse = ApiResponse.error(
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                exception.getMessage(),
                traceId
        );

        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String traceId = getTraceId(request);
        String enumKey = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage();
        
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        try {
            if (enumKey != null) {
                errorCode = ErrorCode.valueOf(enumKey);
            }
        } catch (IllegalArgumentException e) {
            // Keep default INVALID_REQUEST
        }

        String errorMessage = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage();

        log.warn("ValidationException [traceId: {}]: {}", traceId, errorMessage);

        ApiResponse<Object> apiResponse = ApiResponse.error(
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                errorMessage,
                traceId
        );

        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException exception, HttpServletRequest request) {
        ErrorCode errorCode = ErrorCode.FORBIDDEN;
        String traceId = getTraceId(request);

        ApiResponse<Object> apiResponse = ApiResponse.error(
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                errorCode.getMessage(),
                traceId
        );

        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception exception, HttpServletRequest request) {
        String traceId = getTraceId(request);
        log.error("Unhandled Exception [traceId: {}]: ", traceId, exception);

        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        ApiResponse<Object> apiResponse = ApiResponse.error(
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                exception.getMessage(),
                traceId
        );

        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiResponse);
    }

    private String getTraceId(HttpServletRequest request) {
        String traceId = request.getHeader("X-Trace-Id");
        return (traceId != null && !traceId.isBlank()) ? traceId : UUID.randomUUID().toString();
    }
}
