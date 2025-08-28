package com.application.bus;


import com.application.handler.CommandHandler;
import com.domain.command.UserCommand;
import com.domain.exception.ExceptionMessage;
import com.domain.result.OperationResult;
import io.github.resilience4j.core.functions.Either;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.List;

@RequiredArgsConstructor
public class SimpleUserCommandBus implements UserCommandBus {

    private final Map<Class<?>, CommandHandler<?>> handlers;

    public SimpleUserCommandBus(List<CommandHandler<?>> handlerList) {
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(CommandHandler::commandType, h -> h));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C extends UserCommand> CompletionStage<Either<ExceptionMessage, OperationResult>> send(C command) {
        CommandHandler<C> handler = (CommandHandler<C>) handlers.get(command.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for command: " + command.getClass().getSimpleName());
        }
        return handler.handle(command);
    }
}
