package com.application.handler;

import com.domain.command.GetUserById;
import com.domain.dto.UserDTO;
import com.domain.exception.ExceptionMessage;
import com.domain.model.User;
import com.domain.repository.UserRepository;
import com.domain.result.OperationResult;
import io.github.resilience4j.core.functions.Either;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@RequiredArgsConstructor
public class GetUserByIdHandler implements CommandHandler<GetUserById> {

    private final UserRepository userRepository;

    @Override
    public CompletionStage<Either<ExceptionMessage, OperationResult>> handle(GetUserById command) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                User user = userRepository.findById(command.getUserId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

                UserDTO dto = new UserDTO(
                        user.getId(),
                        user.getFullName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getAvatarUrl(),
                        user.getGender(),
                        user.getDateOfBirth(),
                        user.getAddress()
                );

                return Either.right(OperationResult.success(dto));
            } catch (Exception e) {
                return Either.left(new ExceptionMessage(500, "FETCH_FAILED", e.getMessage()));
            }
        });
    }

    @Override
    public Class<GetUserById> commandType() {
        return GetUserById.class;
    }
}
