package com.application.handler;

import com.domain.command.RegisterUser;
import com.domain.result.OperationResult;
import com.domain.service.UserDomainService;
import com.error.exception.ExceptionMessage;
import io.github.resilience4j.core.functions.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionStage;

@Component
@RequiredArgsConstructor
public class RegisterUserHandler implements CommandHandler<RegisterUser> {

    private final UserDomainService userDomainService;

    @Override
    public CompletionStage<Either<ExceptionMessage, OperationResult>> handle(RegisterUser command) {
        // ✅ Chuyển toàn bộ xử lý logic sang domain service
        return userDomainService.registerUser(command);
    }

    @Override
    public Class<RegisterUser> commandType() {
        return RegisterUser.class;
    }
}
