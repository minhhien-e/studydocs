package com.application;

import com.domain.dto.UserDTO;
import com.domain.exception.ExceptionMessage;
import com.domain.result.OperationResult;
import io.github.resilience4j.core.functions.Either;

import java.util.concurrent.CompletionStage;

public interface ManageUserService {
    CompletionStage<Either<ExceptionMessage, OperationResult>> registerUser(UserDTO userDTO);

    CompletionStage<Either<ExceptionMessage, OperationResult>> updateUser(UserDTO userDTO);

    CompletionStage<Either<ExceptionMessage, OperationResult>> getUserById(String id);
}
