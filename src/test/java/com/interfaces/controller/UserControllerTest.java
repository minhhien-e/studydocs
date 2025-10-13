package com.interfaces.controller;

import com.application.ManageUserService;
import com.domain.dto.UserDTO;
import com.domain.result.OperationResult;
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
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserControllerTest {

    private ManageUserService manageUserService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        // ✅ mock interface (không ép kiểu sang impl)
        manageUserService = Mockito.mock(ManageUserService.class);
        userController = new UserController(manageUserService);
    }
    private RegisterRequest buildSampleRequest() {
        RegisterRequest req = new RegisterRequest();
        req.setFullName("Lâm Bảo Duy");
        req.setUsername("duy123");
        req.setEmail("duy@example.com");
        req.setPhoneNumber("0987654321");
        req.setAvatarUrl("https://example.com/avatar.png");
        req.setGender("Nam");
        req.setDateOfBirth(LocalDate.of(2003, 10, 10));
        req.setAddress("Hồ Chí Minh");
        return req;
    }


    @Test
    void testRegisterUser_Success() throws Exception {
        // given
        RegisterRequest request = buildSampleRequest();


        OperationResult successResult = OperationResult.of("User registered",null);
        Either<ExceptionMessage, OperationResult> either =
                Either.right(successResult);

        // when
        Mockito.when(manageUserService.registerUser(any(UserDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(either));

        Callable<CompletionStage<ResponseEntity<?>>> callable =
                userController.register(request, "trace-001");

        // then
        ResponseEntity<?> response = callable.call().toCompletableFuture().get();
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(apiResponse);
        assertEquals("Đăng ký thành công", apiResponse.message());
    }

    @Test
    void testRegisterUser_Fail() throws Exception {
        // given
        RegisterRequest request = buildSampleRequest();


        ExceptionMessage error = new ExceptionMessage(400,"", "Bad Request", "Email already exists");
        Either<ExceptionMessage, OperationResult> either =
                Either.left(error);

        Mockito.when(manageUserService.registerUser(any(UserDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(either));

        // when
        Callable<CompletionStage<ResponseEntity<?>>> callable =
                userController.register(request, "trace-002");

        ResponseEntity<?> response = callable.call().toCompletableFuture().get();
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();

        // then
        assertEquals(400, response.getStatusCode().value());
        assertEquals("Email already exists", apiResponse.message());
    }

    @Test
    void testGetUserById_Success() throws Exception {
        // given
        OperationResult successResult = OperationResult.of("User found",null);
        Either<ExceptionMessage, OperationResult> either = Either.right(successResult);

        Mockito.when(manageUserService.getUserById("123"))
                .thenReturn(CompletableFuture.completedFuture(either));

        // when
        Callable<CompletionStage<ResponseEntity<?>>> callable =
                userController.getUserByID("123", "trace-003");

        ResponseEntity<?> response = callable.call().toCompletableFuture().get();
        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getBody();

        // then
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Lấy thông tin người dùng thành công", apiResponse.message());
    }
}
