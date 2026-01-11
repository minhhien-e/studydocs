package com.example.followerservice.exception;

import com.example.followerservice.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.UUID;

/**
 * Global exception handler returns ApiResponse with error codes instead of raw messages.
 *
 * <p>Contract: Do NOT return exception message to client - only error codes.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle ApiException - primary exception type with error codes
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException ex) {
        HttpStatus status = ex.getHttpStatus();
        return ResponseEntity
                .status(status)
                .body(ApiResponse.error(status.value(), ex.getErrorCode()));
    }

    /**
     * Handle ResourceNotFoundException - kept for backward compatibility
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), FollowErrorCodes.FOLLOW_NOT_FOUND));
    }

    /**
     * Handle DuplicateResourceException - kept for backward compatibility
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateResource(DuplicateResourceException ex) {
        // Determine specific error code based on message
        int errorCode;
        if (ex.getMessage() != null && ex.getMessage().contains("tự follow")) {
            errorCode = FollowErrorCodes.CANNOT_FOLLOW_SELF;
        } else {
            errorCode = FollowErrorCodes.ALREADY_FOLLOWING;
        }
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), errorCode));
    }

    /**
     * Handle invalid UUID format in request body (JSON deserialization)
     * When Jackson cannot parse string to UUID, it throws HttpMessageNotReadableException
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        // Check if the error is related to UUID parsing
        String message = ex.getMessage();
        boolean isUuidError = message != null && (
            message.contains("UUID") || 
            message.contains("java.util.UUID") ||
            message.contains("Invalid UUID")
        );
        
        int errorCode = isUuidError 
            ? FollowErrorCodes.INVALID_UUID 
            : FollowErrorCodes.REQUEST_VALIDATION_ERROR;
            
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), errorCode));
    }

    /**
     * Handle invalid UUID format in path variables or query parameters
     * When Spring cannot convert string to UUID, it throws MethodArgumentTypeMismatchException
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        // Check if the required type is UUID
        boolean isUuidError = ex.getRequiredType() != null && 
                              UUID.class.isAssignableFrom(ex.getRequiredType());
        
        int errorCode = isUuidError 
            ? FollowErrorCodes.INVALID_UUID 
            : FollowErrorCodes.INVALID_REQUEST_FORMAT;
            
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), errorCode));
    }

    /**
     * Handle invalid UUID format (for programmatic UUID parsing)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidUUID(IllegalArgumentException ex) {
        int errorCode = (ex.getMessage() != null && ex.getMessage().contains("Invalid UUID"))
            ? FollowErrorCodes.INVALID_UUID
            : FollowErrorCodes.INVALID_REQUEST_FORMAT;
            
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), errorCode));
    }

    /**
     * Handle validation errors (@Valid)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), FollowErrorCodes.REQUEST_VALIDATION_ERROR));
    }

    /**
     * Handle unexpected errors
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpected(Exception ex) {
        // Log the exception for debugging (message not sent to client)
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), FollowErrorCodes.FOLLOW_UNKNOWN));
    }
}
