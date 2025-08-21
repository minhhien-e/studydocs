package com.interfaces.controller;

import com.domain.dto.UserDTO;
import com.domain.exception.ExceptionMessage;
import com.interfaces.model.RegisterRequest;
import com.interfaces.model.UserMapper;
import com.interfaces.model.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.application.ManageUserService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ManageUserService manageUserService;

    public UserController(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    @PostMapping("/register")
    public CompletableFuture<ResponseEntity<ApiResponse<Object>>> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        UserDTO user = UserMapper.toDTO(request);

        return manageUserService.registerUser(user)
                .thenApply(result -> {
                    if (result.isLeft()) {
                        ExceptionMessage e = result.getLeft();
                        return ResponseEntity.status(e.getStatusCode())
                                .body(ApiResponse.<Object>error(e.getStatusCode(), e.getMessage(), e.getMessage()));
                    } else {
                        return ResponseEntity.ok(ApiResponse.<Object>success(result.get(), "Đăng ký thành công"));
                    }
                })
                .exceptionally(ex -> ResponseEntity
                        .internalServerError()
                        .body(ApiResponse.<Object>error(500, "INTERNAL_ERROR", "Unexpected error: " + ex.getMessage()))
                );
    }
    @PutMapping("/update")
    public CompletableFuture<ResponseEntity<ApiResponse<Object>>> update(
            @Valid @RequestBody RegisterRequest request
    ) {
        UserDTO userDTO = UserMapper.toDTO(request);

        return manageUserService.updateUser(userDTO)
                .thenApply(result -> {
                    if (result.isLeft()) {
                        ExceptionMessage e = result.getLeft();
                        return ResponseEntity.status(e.getStatusCode())
                                .body(ApiResponse.<Object>error(e.getStatusCode(), e.getMessage(), e.getDescription()));
                    } else {
                        return ResponseEntity.ok(ApiResponse.<Object>success(result.get(), "Cập nhật thành công"));
                    }
                })
                .exceptionally(ex -> ResponseEntity
                        .internalServerError()
                        .body(ApiResponse.<Object>error(500, "INTERNAL_ERROR", "Unexpected error: " + ex.getMessage()))
                );
    }


    @GetMapping("/getUserByID")
    public CompletableFuture<ResponseEntity<ApiResponse<UserDTO>>> getUserByID(@RequestParam String id) {
        return manageUserService.getUserById(id)
                .thenApply(result -> {
                    if (result.isLeft()) {
                        ExceptionMessage e = result.getLeft();
                        return ResponseEntity.status(e.getStatusCode())
                                .body(ApiResponse.<UserDTO>error(e.getStatusCode(), e.getMessage(), e.getMessage()));
                    } else {
                        return ResponseEntity.ok(ApiResponse.<UserDTO>success( (UserDTO)result.get().getData(), "Lấy thông tin người dùng thành công"));
                    }
                })
                .exceptionally(ex -> ResponseEntity
                        .internalServerError()
                        .body(ApiResponse.<UserDTO>error(500, "INTERNAL_ERROR", "Unexpected error: " + ex.getMessage()))
                )
                .toCompletableFuture();
    }

}
