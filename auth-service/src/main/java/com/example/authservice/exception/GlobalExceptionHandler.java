package com.example.authservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException ex, WebRequest request) {
        
        log.error("Authentication error: {} (Code: {})", ex.getMessage(), ex.getErrorCode());
        
        ErrorResponse error = new ErrorResponse(
                ex.getHttpStatus().value(),
                "Authentication error",
                ex.getMessage(),
                getRequestPath(request),
                ex.getErrorCode()
        );

        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(OAuth2AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleOAuth2Exception(
            OAuth2AuthenticationException ex, WebRequest request) {
        
        OAuth2Error oauth2Error = ex.getError();
        log.error("OAuth2 error: {} (Code: {})", oauth2Error.getDescription(), oauth2Error.getErrorCode());
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            "OAuth2 authentication error",
            oauth2Error.getDescription(),
            getRequestPath(request),
            AuthErrorCode.valueOf(oauth2Error.getErrorCode())
        );
        
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationException(
            AuthorizationException ex, WebRequest request) {
        
        log.error("Authorization error: {} (Code: {})", ex.getMessage(), ex.getErrorCode());
        
        ErrorResponse error = new ErrorResponse(
            ex.getHttpStatus().value(),
            "Authorization error",
            ex.getMessage(),
            getRequestPath(request),
            ex.getErrorCode()
        );
        
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            ValidationException ex, WebRequest request) {
        
        log.error("Validation error: {}", ex.getMessage());
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation error",
            ex.getMessage(),
            getRequestPath(request),
            ex.getErrorCode()
        );
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        
        log.error("Unexpected error: ", ex);
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            "An unexpected error occurred. Please try again later.",
            getRequestPath(request),
            AuthErrorCode.INTERNAL_ERROR
        );
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getRequestPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}