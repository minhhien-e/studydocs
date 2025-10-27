package com.example.academicservice.exception;

/**
 * Exception được throw khi tạo resource trùng lặp (ví dụ: slug đã tồn tại)
 */
public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String message) {
        super(message);
    }
}

