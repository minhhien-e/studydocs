package com.application.handler;

import com.domain.command.UpdateUser;
import com.domain.exception.ExceptionMessage;
import com.domain.model.User;
import com.domain.repository.UserRepository;
import com.domain.result.OperationResult;
import io.github.resilience4j.core.functions.Either;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RequiredArgsConstructor
public class UpdateUserHandler implements CommandHandler<UpdateUser> {

    private final UserRepository userRepository;

    @Override
    public CompletionStage<Either<ExceptionMessage, OperationResult>> handle(UpdateUser command) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                User user = userRepository.findById(command.getUserId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

                // cập nhật thông tin
                user.setFullName(command.getFullName());
                user.setEmail(command.getEmail());
                user.setPhoneNumber(Long.parseLong(command.getPhone()));
                user.setAvatarUrl(command.getAvatarUrl());

                userRepository.save(user);

                return Either.right(OperationResult.success("User updated successfully"));
            } catch (Exception e) {
                return Either.left(new ExceptionMessage(500, "UPDATE_FAILED", e.getMessage()));
            }
        });
    }

    @Override
    public Class<UpdateUser> commandType() {
        return UpdateUser.class;
    }
}
