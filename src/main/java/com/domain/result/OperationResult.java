package com.domain.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class OperationResult {
    private String message;
    private Object data;

}
