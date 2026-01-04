package com.example.academicservice.exception;

/**
 * Exception được throw khi không tìm thấy resource trong database.
 * 
 * <p>This is a placeholder class. User will update this with their own implementation.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s không tìm thấy với %s: '%s'", resourceName, fieldName, fieldValue));
    }
}

