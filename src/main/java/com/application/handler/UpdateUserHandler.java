package com.application.handler;

import com.domain.command.UpdateUser;
import com.domain.result.OperationResult;
import com.domain.service.UserDomainService;
import com.error.exception.ExceptionMessage;
import io.github.resilience4j.core.functions.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionStage;

@Component
@RequiredArgsConstructor
public class UpdateUserHandler implements CommandHandler<UpdateUser> {

    private final UserDomainService userDomainService;

    @Override
    public CompletionStage<Either<ExceptionMessage, OperationResult>> handle(UpdateUser command) {
        // Ủy quyền toàn bộ logic cho domain service
        return userDomainService.updateUser(command);
    }

    @Override
    public Class<UpdateUser> commandType() {
        return UpdateUser.class;
    }
}
