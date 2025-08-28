package com.application.handler;

import com.domain.command.UserCommand;
import com.domain.exception.ExceptionMessage;
import com.domain.result.OperationResult;
import io.github.resilience4j.core.functions.Either;

import java.util.concurrent.CompletionStage;

/**
 * Generic CommandHandler
 */
public interface CommandHandler<C extends UserCommand> {
    CompletionStage<Either<ExceptionMessage, OperationResult>> handle(C command);

    /**
     * Cho phép CommandBus biết Handler này xử lý command nào
     */
    Class<C> commandType();
}
