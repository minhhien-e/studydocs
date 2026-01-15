package studydocs.media.api.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import studydocs.media.application.exception.ConcurrentUpdateException;
import studydocs.media.application.port.in.provider.CurrentTraceIdProvider;
import studydocs.media.shared.web.ApiResponse;
import studydocs.media.shared.web.HttpException;
import studydocs.media.application.exception.ApplicationDomainException;
import studydocs.media.api.mapper.ApplicationToHttpExceptionMapper;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final CurrentTraceIdProvider currentTraceIdProvider;
    private final ApplicationToHttpExceptionMapper applicationToHttpExceptionMapper;

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<?> handleHttpException(HttpException e) {
        log.warn("HttpException: {} (Code: {})", e.getMessage(), e.getErrorCode());
        log.debug("Stack trace:", e);
        return ResponseEntity.status(e.getStatusCode()).body(
                ApiResponse.error(e.getStatusCode(), e.getErrorCode(), currentTraceIdProvider.getCurrentTraceId()));
    }

    @ExceptionHandler(ApplicationDomainException.class)
    public ResponseEntity<?> handleDomainException(ApplicationDomainException e) {
        log.warn("DomainException: {}", e.getMessage());
        var httpException = applicationToHttpExceptionMapper.map(e);
        return handleHttpException(httpException);
    }

    @ExceptionHandler(org.springframework.dao.OptimisticLockingFailureException.class)
    public ResponseEntity<?> handleOptimisticLockingFailureException(OptimisticLockingFailureException e) {
        log.warn("OptimisticLockingFailure: {}", e.getMessage());
        return handleDomainException(new ConcurrentUpdateException(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unhandled Exception: ", e);
        return ResponseEntity.status(500).body(ApiResponse.error(500, -1, currentTraceIdProvider.getCurrentTraceId()));
    }
}