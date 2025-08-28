package com.application.impl;

import com.application.ManageUserService;
import com.application.bus.UserCommandBus;
import com.domain.command.GetUserById;
import com.domain.command.RegisterUser;
import com.domain.command.UpdateUser;
import com.domain.dto.UserDTO;
import com.domain.exception.ExceptionMessage;
import com.domain.result.OperationResult;
import io.github.resilience4j.core.functions.Either;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletionStage;

@RequiredArgsConstructor
public class ManageUserServiceImpl implements ManageUserService {

    private final UserCommandBus commandBus; // abstraction để gửi command đi

    @Override
    public CompletionStage<Either<ExceptionMessage, OperationResult>> registerUser(UserDTO userDTO) {
        RegisterUser command = RegisterUser.commandOf(
                userDTO.getFullName(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPhoneNumber(),
                userDTO.getAvatarUrl(),
                userDTO.getGender(),
                userDTO.getDateOfBirth(),
                userDTO.getAddress()
        );
        return commandBus.send(command);
    }

    @Override
    public CompletionStage<Either<ExceptionMessage, OperationResult>> updateUser(UserDTO userDTO) {
        UpdateUser command = UpdateUser.commandOf(
                userDTO.getId(),
                userDTO.getFullName(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPhoneNumber(),
                userDTO.getAvatarUrl(),
                userDTO.getGender(),
                userDTO.getDateOfBirth(),
                userDTO.getAddress()
        );
        return commandBus.send(command);
    }

    @Override
    public CompletionStage<Either<ExceptionMessage, OperationResult>> getUserById(String id) {
        GetUserById command = GetUserById.commandOf(id);
        return commandBus.send(command);
    }
}
