package com.domain.service;

import com.domain.command.RegisterUser;
import com.domain.entity.UserEntity;
import com.domain.repository.UserRepository;
import com.domain.result.OperationResult;
import com.error.ErrorCode;
import com.error.exception.ExceptionMessage;
import io.github.resilience4j.core.functions.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserDomainServiceTest {

    private UserRepository userRepository;
    private UserDomainService userDomainService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userDomainService = new UserDomainService(userRepository);
    }

    @Test
    void registerUser_shouldReturnError_whenUsernameAlreadyExists() throws ExecutionException, InterruptedException {
        // given
        RegisterUser command = RegisterUser.commandOf(
                "Lâm Bảo Duy",
                "duylb",
                "duy@example.com",
                "0123456789",
                "avatar.png",
                "male",
                LocalDate.of(2002, 8, 10),
                "Hồ Chí Minh"
        );

        when(userRepository.existsByUsername(command.getUsername()))
                .thenReturn(CompletableFuture.completedFuture(true));

        // when
        Either<ExceptionMessage, OperationResult> result =
                userDomainService.registerUser(command).toCompletableFuture().get();

        // then
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft().getCode()).isEqualTo(ErrorCode.USER_ALREADY_EXISTS.getCode());
        verify(userRepository, never()).save(any());
    }

    @Test
    void registerUser_shouldSaveUser_whenUsernameDoesNotExist() throws ExecutionException, InterruptedException {
        // given
        RegisterUser command = RegisterUser.commandOf(
                "Lâm Bảo Duy",
                "duylb",
                "duy@example.com",
                "0123456789",
                "avatar.png",
                "male",
                LocalDate.of(2002, 8, 10),
                "Hồ Chí Minh"
        );

        when(userRepository.existsByUsername(command.getUsername()))
                .thenReturn(CompletableFuture.completedFuture(false));

        UserEntity savedUser = new UserEntity(
                "123",
                command.getFullName(),
                command.getUsername(),
                command.getEmail(),
                command.getPhoneNumber(),
                command.getAvatarUrl(),
                command.getGender(),
                command.getDateOfBirth(),
                command.getAddress()
        );

        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(CompletableFuture.completedFuture(Either.right(savedUser)));

        // when
        Either<ExceptionMessage, OperationResult> result =
                userDomainService.registerUser(command).toCompletableFuture().get();

        // then
        assertThat(result.isRight()).isTrue();
        assertThat(result.get().getMessage()).isEqualTo("Đăng ký người dùng thành công");
        verify(userRepository).save(any(UserEntity.class));
    }
}
