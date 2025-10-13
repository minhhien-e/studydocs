package com.error.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateMessageError {
    private String field;
    private Object rejectedValue;
    private String message;
}
