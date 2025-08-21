package com.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
    private int statusCode;;
    private String message;
    private String description;
}
