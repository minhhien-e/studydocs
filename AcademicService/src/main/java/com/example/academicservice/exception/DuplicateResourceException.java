package com.example.academicservice.exception;

/**
 * Exception được throw khi tạo resource trùng lặp (ví dụ: slug đã tồn tại).
 * 
 * <p>This is a placeholder class. User will update this with their own implementation.
 */
public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String message) {
        super(message);
    }
}

