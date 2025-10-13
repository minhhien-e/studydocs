package com.interfaces.model;


import com.error.exception.ValidateMessageError;
import lombok.Data;

import java.util.Set;

@Data
public class ErrorResponse {
    private Set<ValidateMessageError> errors;
}