package com.application.handler;

import com.domain.command.RegisterUser;
import com.domain.exception.ExceptionMessage;
import com.domain.model.User;
import com.domain.repository.UserRepository;
import com.domain.result.OperationResult;
import io.github.resilience4j.core.functions.Either;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RequiredArgsConstructor
public class RegisterUserHandler implements CommandHandler<RegisterUser> {

    private final UserRepository userRepository;

    @Override
    public CompletionStage<Either<ExceptionMessage, OperationResult>> handle(RegisterUser command) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                User user = new User(
                        command.getUsername(),
                        command.getEmail(),
                        command.getPhone(),
                        command.getFullName(),
                        command.getPassword()
                );

                userRepository.save(user);

                return Either.right(OperationResult.success("User registered successfully"));
            } catch (Exception e) {
                return Either.left(new ExceptionMessage(500, "REGISTER_FAILED", e.getMessage()));
            }
        });
    }

    @Override
    public Class<RegisterUser> commandType() {
        return RegisterUser.class;
    }
}
