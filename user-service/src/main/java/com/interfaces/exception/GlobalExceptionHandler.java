package com.interfaces.exception;

import com.error.exception.DomainException;
import com.error.exception.HttpExeption;
import com.error.exception.InfrastructureException;
import com.interfaces.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Sinh traceId mới cho mỗi request
     */
    private String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    @ExceptionHandler(DomainException.class)
    public ApiResponse<?> handleDomainException(DomainException ex) {
        String traceId = generateTraceId();
        LOG.error("DomainException in method {}: traceId={}", ex.getMethodName(), traceId);

        return ApiResponse.error(
                ex.getErrorCode().getStatus().value(),
                ex.getErrorCode().getCode(),
                traceId
        );
    }

    @ExceptionHandler(InfrastructureException.class)
    public ApiResponse<?> handleInfrastructureException(InfrastructureException ex) {
        String traceId = generateTraceId();
        LOG.error("InfrastructureException in method {}: traceId={}", ex.getMethod(), traceId);

        return ApiResponse.error(
                ex.getErrorCode().getStatus().value(),
                ex.getErrorCode().getCode(),
                traceId
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidationException(MethodArgumentNotValidException ex) {
        String traceId = generateTraceId();
        String field = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getField()
                : "unknown";

        LOG.error("Validation failed for field {}: traceId={}", field, traceId);

        return ApiResponse.error(
                400,
                1003, // U003 - INVALID_USER_INPUT
                traceId
        );
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGeneralException(Exception ex) {
        String traceId = generateTraceId();
        LOG.error("Unhandled exception: traceId={}, exception={}", traceId, ex.getMessage(), ex);

        return ApiResponse.error(
                500,
                null, // INTERNAL_SERVER_ERROR, nếu bạn muốn có code có thể đặt
                traceId
        );
    }
    @ExceptionHandler(HttpExeption.class)
    public ApiResponse<?> handleHttpException(HttpExeption ex) {
        String traceId = generateTraceId();
        LOG.error("Unhandled exception: traceId={}, exception={}", traceId, ex.getMessage(), ex);

        return ApiResponse.error(
                ex.getStatusCode(),
                ex.getErorCode(),
                traceId
        );
    }
}
