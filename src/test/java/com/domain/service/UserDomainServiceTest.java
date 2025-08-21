package com.domain.service;

import com.domain.dto.UserDTO;
import com.domain.entity.UserEntity;
import com.domain.exception.ExceptionFactory;
import com.domain.repository.UserRepository;
import com.domain.exception.ExceptionMessage;
import io.github.resilience4j.core.functions.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDomainServiceTest {
    @Mock
    private UserRepository userRepository;

    private ExceptionFactory exceptionFactory;
    private UserDomainService service;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        exceptionFactory = mock(ExceptionFactory.class);
        service = new UserDomainService(userRepository, exceptionFactory);

        // Mock exceptionFactory cho các loại lỗi
        when(exceptionFactory.badRequest(anyString(), anyString()))
                .thenAnswer(inv -> new ExceptionMessage(400, inv.getArgument(0), inv.getArgument(1)));
        when(exceptionFactory.notFound(anyString(), anyString()))
                .thenAnswer(inv -> new ExceptionMessage(404, inv.getArgument(0), inv.getArgument(1)));
        when(exceptionFactory.systemError(anyString()))
                .thenAnswer(inv -> new ExceptionMessage(500, inv.getArgument(0), inv.getArgument(0)));
    }

    @Test
    void register_usernameExists_returnsLeft() {
        UserDTO dto = new UserDTO();
        dto.setUsername("john");

        when(userRepository.existsByUsername("john"))
                .thenReturn(CompletableFuture.completedFuture(true));

        Either<ExceptionMessage, String> result = service.register(dto).toCompletableFuture().join();

        assertTrue(result.isLeft());
        assertEquals(400, result.getLeft().getStatusCode());
    }

    @Test
    void register_newUser_returnsRight() {
        UserDTO dto = new UserDTO();
        dto.setUsername("john");

        when(userRepository.existsByUsername("john"))
                .thenReturn(CompletableFuture.completedFuture(false));
        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(CompletableFuture.completedFuture(Either.right(new UserEntity())));

        Either<ExceptionMessage, String> result = service.register(dto).toCompletableFuture().join();

        assertTrue(result.isRight());
        assertEquals("Đăng ký thành công", result.get());
    }

    @Test
    void register_saveThrowsException_returnsLeft() {
        UserDTO dto = new UserDTO();
        dto.setUsername("john");

        when(userRepository.existsByUsername("john"))
                .thenReturn(CompletableFuture.completedFuture(false));
        when(userRepository.save(any()))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("DB error")));

        Either<ExceptionMessage, String> result = service.register(dto).toCompletableFuture().join();

        assertTrue(result.isLeft());
        assertEquals(500, result.getLeft().getStatusCode());
    }

    @Test
    void update_invalidData_returnsLeft() {
        UserDTO dto = new UserDTO(); // thiếu username, email

        Either<ExceptionMessage, String> result = service.update(dto).toCompletableFuture().join();

        assertTrue(result.isLeft());
        assertEquals(400, result.getLeft().getStatusCode());
    }

    @Test
    void update_userNotFound_returnsLeft() {
        UserDTO dto = new UserDTO();
        dto.setId("1");
        dto.setUsername("john");
        dto.setEmail("john@example.com");

        when(userRepository.existsByUserId("1"))
                .thenReturn(CompletableFuture.completedFuture(false));

        Either<ExceptionMessage, String> result = service.update(dto).toCompletableFuture().join();

        assertTrue(result.isLeft());
        assertEquals(404, result.getLeft().getStatusCode());
    }

    @Test
    void update_success_returnsRight() {
        UserDTO dto = new UserDTO();
        dto.setId("1");
        dto.setUsername("john");
        dto.setEmail("john@example.com");

        when(userRepository.existsByUserId("1"))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(userRepository.updateUser(any(UserEntity.class)))
                .thenReturn(CompletableFuture.completedFuture(null));

        Either<ExceptionMessage, String> result = service.update(dto).toCompletableFuture().join();

        assertTrue(result.isRight());
        assertEquals("Cập nhật thành công", result.get());
    }

    @Test
    void getUser_notFound_returnsLeft() {
        when(userRepository.existsByUserId("1"))
                .thenReturn(CompletableFuture.completedFuture(false));

        Either<ExceptionMessage, UserDTO> result = service.getUser("1").toCompletableFuture().join();

        assertTrue(result.isLeft());
        assertEquals(404, result.getLeft().getStatusCode());
    }

    @Test
    void getUser_foundEmptyOptional_returnsLeft() {
        when(userRepository.existsByUserId("1"))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(userRepository.findById("1"))
                .thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        Either<ExceptionMessage, UserDTO> result = service.getUser("1").toCompletableFuture().join();

        assertTrue(result.isLeft());
        assertEquals(404, result.getLeft().getStatusCode());
    }

    @Test
    void getUser_found_returnsRight() {
        when(userRepository.existsByUserId("1"))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(userRepository.findById("1"))
                .thenReturn(CompletableFuture.completedFuture(
                        Optional.of(new UserEntity("1", "John", "john", "john@example.com", 0, null, null, null, null))
                ));

        Either<ExceptionMessage, UserDTO> result = service.getUser("1").toCompletableFuture().join();

        assertTrue(result.isRight());
        assertEquals("john", result.get().getUsername());
    }
}

