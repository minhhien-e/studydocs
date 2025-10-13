package com.interfaces.controller;

import com.application.ManageUserService;
import com.domain.dto.UserDTO;
import com.domain.result.OperationResult;
import com.error.ErrorCode;
import com.error.exception.ExceptionMessage;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.RegisterRequest;
import io.github.resilience4j.core.functions.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private ManageUserService manageUserService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        manageUserService = Mockito.mock(ManageUserService.class);
        userController = new UserController((com.application.impl.ManageUserServiceImpl) manageUserService);
    }

    @Test
    void register_shouldReturnSuccess_whenUserIsRegistered() throws ExecutionException, InterruptedException {
        // Given
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Lâm Bảo Duy");
        request.setUsername("duylb");
        request.setEmail("duy@example.com");
        request.setPhoneNumber("0123456789");
        request.setGender("male");
        request.setAvatarUrl("avatar.png");
        request.setDateOfBirth(LocalDate.of(2002, 8, 10));
        request.setAddress("Hồ Chí Minh");

        OperationResult result = OperationResult.of("Đăng ký người dùng thành công", new UserDTO());
        when(manageUserService.registerUser(any(UserDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(Either.right(result)));

        // When
        ResponseEntity<?> response = null;
        try {
            response = userController.register(request, "trace-123").call().toCompletableFuture().get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertThat(body.message()).contains("Đăng ký thành công");

        verify(manageUserService, times(1)).registerUser(any(UserDTO.class));
    }

    @Test
    void register_shouldReturnError_whenUserAlreadyExists() throws Exception {
        // Given
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Lâm Bảo Duy");
        request.setUsername("duylb");
        request.setEmail("duy@example.com");

        ExceptionMessage error = new ExceptionMessage(
                ErrorCode.USER_ALREADY_EXISTS,
                "Username 'duylb' đã tồn tại"
        );

        when(manageUserService.registerUser(any(UserDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(Either.left(error)));

        // When
        ResponseEntity<?> response = userController.register(request, "trace-456").call().toCompletableFuture().get();

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(ErrorCode.USER_ALREADY_EXISTS.getStatus());
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertThat(body.message()).contains("Username 'duylb' đã tồn tại");
    }

    @Test
    void getUserById_shouldReturnSuccess_whenUserExists() throws Exception {
        // Given
        String id = "123";
        UserDTO user = new UserDTO();
        user.setId(id);

        OperationResult result = OperationResult.of("OK", user);
        when(manageUserService.getUserById(id))
                .thenReturn(CompletableFuture.completedFuture(Either.right(result)));

        // When
        ResponseEntity<?> response = userController.getUserByID(id, "trace-789").call().toCompletableFuture().get();

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertThat(body.message()).contains("Lấy thông tin người dùng thành công");
        verify(manageUserService).getUserById(id);
    }

    @Test
    void update_shouldReturnSuccess_whenUpdateSuccessful() throws Exception {
        // Given
        RegisterRequest request = new RegisterRequest();
        request.setUsername("duylb");
        request.setEmail("duy@example.com");

        OperationResult result = OperationResult.of("Cập nhật thành công", null);
        when(manageUserService.updateUser(any(UserDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(Either.right(result)));

        // When
        ResponseEntity<?> response = userController.update(request, "trace-999").call().toCompletableFuture().get();

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertThat(body.message()).contains("Cập nhật thành công");
        verify(manageUserService).updateUser(any(UserDTO.class));
    }
}
