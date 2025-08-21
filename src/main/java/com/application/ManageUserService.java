package com.application;

import com.domain.dto.UserDTO;
import com.domain.service.UserDomainService;
import com.domain.exception.ExceptionMessage;
import com.domain.result.OperationResult;

import io.github.resilience4j.core.functions.Either;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ManageUserService {

    private final UserDomainService userDomainService;

    public ManageUserService(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    /**
     * Đăng ký người dùng mới
     */
    public CompletableFuture<Either<ExceptionMessage, OperationResult>> registerUser(UserDTO userDTO) {
        return userDomainService.register(userDTO).toCompletableFuture();
    }

    /**
     * Cập nhật thông tin người dùng
     */
    public CompletableFuture<Either<ExceptionMessage, OperationResult>> updateUser(UserDTO userDTO) {
        return userDomainService.update(userDTO).toCompletableFuture();
    }

    /**
     * Lấy thông tin người dùng theo ID
     */
    public CompletableFuture<Either<ExceptionMessage, OperationResult>> getUserById(String id) {
        return userDomainService.getUser(id).toCompletableFuture();
    }
}
