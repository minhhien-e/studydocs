package com.application.bus;

import com.domain.command.UserCommand;
import com.domain.exception.ExceptionMessage;
import com.domain.result.OperationResult;
import io.github.resilience4j.core.functions.Either;

import java.util.concurrent.CompletionStage;

public interface UserCommandBus {
    <C extends UserCommand> CompletionStage<Either<ExceptionMessage, OperationResult>> send(C command);
}
