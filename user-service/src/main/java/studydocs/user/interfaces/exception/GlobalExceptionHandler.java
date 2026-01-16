package studydocs.user.interfaces.exception;

import studydocs.user.error.exception.DomainException;
import studydocs.user.error.exception.HttpException;
import studydocs.user.error.exception.InfrastructureException;
import studydocs.user.interfaces.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Sinh traceId mới cho mỗi request
     */
    private String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    @ExceptionHandler(DomainException.class)
    public ApiResponse<?> handleDomainException(DomainException ex) {
        String traceId = generateTraceId();
        log.error("DomainException in method {}: traceId={}", ex.getMethodName(), traceId);

        return ApiResponse.error(
                ex.getErrorCode().getStatus().value(),
                ex.getErrorCode().getCode(),
                traceId
        );
    }

    @ExceptionHandler(InfrastructureException.class)
    public ApiResponse<?> handleInfrastructureException(InfrastructureException ex) {
        String traceId = generateTraceId();
        log.error("InfrastructureException in method {}: traceId={}", ex.getMethod(), traceId);

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

        log.error("Validation failed for field {}: traceId={}", field, traceId);

        return ApiResponse.error(
                400,
                1003, // U003 - INVALID_USER_INPUT
                traceId
        );
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGeneralException(Exception ex) {
        String traceId = generateTraceId();
        log.error("Unhandled exception: traceId={}, exception={}", traceId, ex.getMessage(), ex);

        return ApiResponse.error(
                500,
                null, // INTERNAL_SERVER_ERROR, nếu bạn muốn có code có thể đặt
                traceId
        );
    }
    @ExceptionHandler(HttpException.class)
    public ApiResponse<?> handleHttpException(HttpException ex) {
        String traceId = generateTraceId();
        log.error("Unhandled exception: traceId={}, exception={}", traceId, ex.getMessage(), ex);

        return ApiResponse.error(
                ex.getStatusCode(),
                ex.getErrorCode(),
                traceId
        );
    }

}
