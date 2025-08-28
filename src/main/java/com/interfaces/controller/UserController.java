package com.interfaces.controller;

import com.application.ManageUserService;
import com.domain.dto.UserDTO;
import com.domain.exception.ExceptionMessage;
import com.domain.result.OperationResult;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.RegisterRequest;
import com.interfaces.model.UserMapper;

import io.github.resilience4j.core.functions.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final ManageUserService manageUserService;

    public UserController(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    @PostMapping("/register")
    public Callable<CompletionStage<ResponseEntity<?>>> register(@Valid @RequestBody RegisterRequest request) {
        LOG.debug("Register request {}", request);

        return () -> {
            LOG.debug("Callable register...");

            UserDTO user = UserMapper.toDTO(request);

            CompletionStage<Either<ExceptionMessage, OperationResult>> promise = manageUserService.registerUser(user);
            return promise.thenApply(result -> result.fold(
                    error -> ResponseEntity.status(error.getStatusCode())
                            .body(ApiResponse.error(error.getStatusCode(), error.getMessage(), error.getDescription())),
                    success -> ResponseEntity.ok(ApiResponse.success(success, "Đăng ký thành công"))
            ));
        };
    }

    @PutMapping("/update")
    public Callable<CompletionStage<ResponseEntity<?>>> update(@Valid @RequestBody RegisterRequest request) {
        LOG.debug("Update request {}", request);

        return () -> {
            LOG.debug("Callable update...");

            UserDTO userDTO = UserMapper.toDTO(request);

            CompletionStage<Either<ExceptionMessage, OperationResult>> promise = manageUserService.updateUser(userDTO);
            return promise.thenApply(result -> result.fold(
                    error -> ResponseEntity.status(error.getStatusCode())
                            .body(ApiResponse.error(error.getStatusCode(), error.getMessage(), error.getDescription())),
                    success -> ResponseEntity.ok(ApiResponse.success(success, "Cập nhật thành công"))
            ));
        };
    }

    @GetMapping("/getUserByID")
    public Callable<CompletionStage<ResponseEntity<?>>> getUserByID(@RequestParam String id) {
        LOG.debug("Get user by id {}", id);

        return () -> {
            LOG.debug("Callable getUserByID...");

            CompletionStage<Either<ExceptionMessage, OperationResult>> promise = manageUserService.getUserById(id);
            return promise.thenApply(result -> result.fold(
                    error -> ResponseEntity.status(error.getStatusCode())
                            .body(ApiResponse.error(error.getStatusCode(), error.getMessage(), error.getDescription())),
                    success -> ResponseEntity.ok(ApiResponse.success(success.getData(), "Lấy thông tin người dùng thành công"))
            ));
        };
    }
}
