package com.example.academicservice.exception;

import com.example.academicservice.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.UUID;

/**
 * Global exception handler để handle các exception và trả về error response chuẩn.
 *
 * <p>Contract: do NOT return exception message to client.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException ex) {
        HttpStatus status = ex.getHttpStatus();
        return ResponseEntity
                .status(status)
                .body(ApiResponse.error(status.value(), ex.getErrorCode()));
    }

    /**
     * Handle invalid UUID format (for programmatic UUID parsing)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        int errorCode = (ex.getMessage() != null && ex.getMessage().contains("Invalid UUID"))
            ? AcademicErrorCodes.INVALID_UUID
            : AcademicErrorCodes.UNKNOWN_ACADEMIC_ERROR;
            
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), AcademicErrorCodes.UNKNOWN_ACADEMIC_ERROR));
    }

    /**
     * Handle invalid UUID format in request body (JSON deserialization)
     * When Jackson cannot parse string to UUID, it throws HttpMessageNotReadableException
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotReadable(HttpMessageNotReadableException ex) {
        // Check if the error is related to UUID parsing
        String message = ex.getMessage();
        boolean isUuidError = message != null && (
            message.contains("UUID") || 
            message.contains("java.util.UUID") ||
            message.contains("Invalid UUID")
        );
        
        int errorCode = isUuidError 
            ? AcademicErrorCodes.INVALID_UUID 
            : AcademicErrorCodes.UNKNOWN_ACADEMIC_ERROR;
            
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
        // Check if the required type is UUID (AcademicService uses UUID for IDs)
        boolean isUuidError = ex.getRequiredType() != null && 
                              UUID.class.isAssignableFrom(ex.getRequiredType());
        
        int errorCode = isUuidError 
            ? AcademicErrorCodes.INVALID_UUID 
            : AcademicErrorCodes.UNKNOWN_ACADEMIC_ERROR;
            
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), errorCode));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpected(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), AcademicErrorCodes.UNKNOWN_ACADEMIC_ERROR));
    }

    @ExceptionHandler(RequestApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleRequestApiException(RequestApiException ex) {
        int status = ex.getStatus();
        int errorCode = ex.getErrorCode();
        return ResponseEntity
                .status(status)
                .body(ApiResponse.error(status, errorCode));
    }

    /**
     * Handle ResourceNotFoundException - maps to appropriate AcademicErrorCode based on resource type.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(ResourceNotFoundException ex) {
        int errorCode = determineResourceNotFoundErrorCode(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), errorCode));
    }

    /**
     * Handle DuplicateResourceException - maps to appropriate AcademicErrorCode based on resource type.
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateResource(DuplicateResourceException ex) {
        int errorCode = determineDuplicateErrorCode(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(HttpStatus.CONFLICT.value(), errorCode));
    }

    /**
     * Determine error code for ResourceNotFoundException based on exception message.
     * Parses the message to identify resource type (University, Faculty, Department, Subject).
     */
    private int determineResourceNotFoundErrorCode(String message) {
        if (message == null) {
            return AcademicErrorCodes.UNKNOWN_ACADEMIC_ERROR;
        }

        String msg = message.toLowerCase();

        // Check for ID mismatch scenarios (resource doesn't belong to university)
        if (msg.contains("không thuộc university") || msg.contains("không thuộc")) {
            if (msg.contains("university")) {
                return AcademicErrorCodes.UNIVERSITY_ID_MISMATCH;
            } else if (msg.contains("faculty") || msg.contains("khoa")) {
                return AcademicErrorCodes.FACULTY_ID_MISMATCH;
            } else if (msg.contains("department") || msg.contains("bộ môn")) {
                return AcademicErrorCodes.DEPARTMENT_ID_MISMATCH;
            } else if (msg.contains("subject") || msg.contains("môn học")) {
                return AcademicErrorCodes.SUBJECT_ID_MISMATCH;
            }
        }

        // Check for resource not found scenarios
        if (msg.startsWith("university") || msg.contains("university")) {
            return AcademicErrorCodes.UNIVERSITY_NOT_FOUND;
        } else if (msg.startsWith("faculty") || msg.contains("faculty") || msg.contains("khoa")) {
            return AcademicErrorCodes.FACULTY_NOT_FOUND;
        } else if (msg.startsWith("department") || msg.contains("department") || msg.contains("bộ môn")) {
            return AcademicErrorCodes.DEPARTMENT_NOT_FOUND;
        } else if (msg.startsWith("subject") || msg.contains("subject") || msg.contains("môn học")) {
            return AcademicErrorCodes.SUBJECT_NOT_FOUND;
        }

        return AcademicErrorCodes.UNKNOWN_ACADEMIC_ERROR;
    }

    /**
     * Determine error code for DuplicateResourceException based on exception message.
     * Parses the message to identify which resource type has a duplicate slug.
     */
    private int determineDuplicateErrorCode(String message) {
        if (message == null) {
            return AcademicErrorCodes.UNKNOWN_ACADEMIC_ERROR;
        }

        String msg = message.toLowerCase();

        if (msg.contains("university") || msg.contains("trường đại học")) {
            return AcademicErrorCodes.UNIVERSITY_SLUG_EXISTS;
        } else if (msg.contains("faculty") || msg.contains("khoa")) {
            return AcademicErrorCodes.FACULTY_SLUG_EXISTS;
        } else if (msg.contains("department") || msg.contains("bộ môn")) {
            return AcademicErrorCodes.DEPARTMENT_SLUG_EXISTS;
        } else if (msg.contains("subject") || msg.contains("môn học")) {
            return AcademicErrorCodes.SUBJECT_SLUG_EXISTS;
        }

        return AcademicErrorCodes.UNKNOWN_ACADEMIC_ERROR;
    }
}

