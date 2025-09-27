package studydocs.notificationservice.infrastructure.inbound.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;
import studydocs.notificationservice.shared.exception.abstracts.InfrastructureException;

import static studydocs.notificationservice.shared.helper.HttpStatusCodeExtractor.extractFromDomain;
import static studydocs.notificationservice.shared.helper.HttpStatusCodeExtractor.extractFromInfrastructure;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> domainException(final DomainException e) {
        int statusCode = extractFromDomain(e.getErrorCode());
        String message = e.getMessage();
        String errorCode = e.getErrorCode().name();
        return ResponseEntity.status(statusCode).body(ApiResponse.error(statusCode, errorCode, message));
    }

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<?> infrastructureException(final InfrastructureException e) {
        int statusCode = extractFromInfrastructure(e.getErrorCode());
        String message = e.getMessage();
        String errorCode = e.getErrorCode().name();
        return ResponseEntity.status(statusCode).body(ApiResponse.error(statusCode, errorCode, message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(final Exception e) {
        return ResponseEntity.internalServerError().body(ApiResponse.error(500, "InternalServerError", "Error occurred"));
    }
}
