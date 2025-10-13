package com.application.handler;

import com.domain.command.GetUserById;
import com.domain.result.OperationResult;
import com.domain.service.UserDomainService;
import com.error.exception.ExceptionMessage;
import io.github.resilience4j.core.functions.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionStage;

@Component
@RequiredArgsConstructor
public class GetUserByIdHandler implements CommandHandler<GetUserById> {

    private final UserDomainService userDomainService;

    @Override
    public CompletionStage<Either<ExceptionMessage, OperationResult>> handle(GetUserById command) {
        // ✅ Gọi domain service để xử lý nghiệp vụ
        return userDomainService.getUserById(command);
    }

    @Override
    public Class<GetUserById> commandType() {
        return GetUserById.class;
    }
}
