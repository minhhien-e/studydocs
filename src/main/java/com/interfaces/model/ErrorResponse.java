package com.interfaces.model;


import com.domain.exception.ValidateMessageError;
import lombok.Data;

import java.util.Set;

@Data
public class ErrorResponse {
    private Set<ValidateMessageError> errors;
}