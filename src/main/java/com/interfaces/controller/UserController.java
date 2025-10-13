package com.interfaces.controller;

import com.application.ManageUserService;
import com.domain.dto.UserDTO;
import com.error.exception.ExceptionMessage;
import com.domain.result.OperationResult;
import com.helper.HelperMap;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.RegisterRequest;

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
    public Callable<CompletionStage<ResponseEntity<?>>> register(
            @Valid @RequestBody RegisterRequest request,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        return () -> {
            LOG.info("[traceId: {}] Đã nhận request register", traceId);

            UserDTO user = HelperMap.INSTANCE.registerRequesttoUserDTO(request);

            CompletionStage<Either<ExceptionMessage, OperationResult>> promise =
                    manageUserService.registerUser(user);

            return promise.thenApply(result -> result.fold(
                    error -> {
                        LOG.error("[traceId: {}] ❌ Error registering user: status={}, message={}, desc={}",
                                traceId,
                                error.getStatusCode(),
                                error.getMessage(),
                                error.getDescription());
                        return ResponseEntity.status(error.getStatusCode())
                                .body(ApiResponse.error(error.getStatusCode(), error.getMessage(), error.getDescription()));
                    },
                    success -> {
                        LOG.info("[traceId: {}] ✅ Successfully registered user", traceId);
                        return ResponseEntity.ok(ApiResponse.success(success, "Đăng ký thành công"));
                    }
            ));
        };
    }


    @PutMapping("/update")
    public Callable<CompletionStage<ResponseEntity<?>>> update(
            @Valid @RequestBody RegisterRequest request,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        return () -> {
            LOG.info("[traceId: {}] Đã nhận request update {}", traceId, request);

            UserDTO userDTO =  HelperMap.INSTANCE.registerRequesttoUserDTO(request);

            CompletionStage<Either<ExceptionMessage, OperationResult>> promise = manageUserService.updateUser(userDTO);
            return promise.thenApply(result -> result.fold(
                    error -> ResponseEntity.status(error.getStatusCode())
                            .body(ApiResponse.error(error.getStatusCode(), error.getMessage(), error.getDescription())),
                    success -> {
                        LOG.info("[traceId: {}] ✅ Successfully updated user", traceId);
                        return ResponseEntity.ok(ApiResponse.success(success, "Cập nhật thành công"));
                    }
            ));
        };
    }

    @GetMapping("/getUserByID")
    public Callable<CompletionStage<ResponseEntity<?>>> getUserByID(
            @RequestParam String id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        return () -> {
            LOG.info("[traceId: {}] Đã nhận request getUserByID id={}", traceId, id);

            CompletionStage<Either<ExceptionMessage, OperationResult>> promise = manageUserService.getUserById(id);
            return promise.thenApply(result -> result.fold(
                    error -> ResponseEntity.status(error.getStatusCode())
                            .body(ApiResponse.error(error.getStatusCode(), error.getMessage(), error.getDescription())),
                    success -> {
                        LOG.info("[traceId: {}] ✅ Successfully fetched user id={}", traceId, id);
                        return ResponseEntity.ok(ApiResponse.success(success.getData(), "Lấy thông tin người dùng thành công"));
                    }
            ));
        };
    }
}

