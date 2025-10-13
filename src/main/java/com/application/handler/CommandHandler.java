package com.application.handler;

import com.domain.command.UserCommand;
import com.error.exception.ExceptionMessage;
import com.domain.result.OperationResult;
import io.github.resilience4j.core.functions.Either;

import java.util.concurrent.CompletionStage;


public interface CommandHandler<C extends UserCommand> {
    CompletionStage<Either<ExceptionMessage, OperationResult>> handle(C command);

    Class<C> commandType();
}
