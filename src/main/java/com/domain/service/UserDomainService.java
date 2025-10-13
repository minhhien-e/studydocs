package com.domain.service;

import com.domain.command.GetUserById;
import com.domain.command.RegisterUser;
import com.domain.command.UpdateUser;
import com.domain.dto.UserDTO;
import com.domain.entity.UserEntity;
import com.domain.repository.UserRepository;
import com.domain.result.OperationResult;
import com.error.ErrorCode;
import com.error.exception.ExceptionMessage;
import com.helper.HelperMap;
import io.github.resilience4j.core.functions.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    /**
     * Đăng ký người dùng mới.
     */
    public CompletionStage<Either<ExceptionMessage, OperationResult>> registerUser(RegisterUser command) {
        // Kiểm tra username đã tồn tại chưa
        return userRepository.existsByUsername(command.getUsername())
                .thenCompose(exists -> {
                    if (exists) {
                        return CompletableFuture.completedFuture(
                                Either.left(new ExceptionMessage(
                                        ErrorCode.USER_ALREADY_EXISTS,
                                        "Username '" + command.getUsername() + "' đã tồn tại"
                                ))
                        );
                    }

                    // Tạo user entity
                    UserEntity user = new UserEntity(null,
                            command.getFullName(),
                            command.getUsername(),
                            command.getEmail(),
                            command.getPhoneNumber(),
                            command.getAvatarUrl(),
                            command.getGender(),
                            command.getDateOfBirth(),
                            command.getAddress()
                    );

                    // Lưu user
                    return userRepository.save(user)
                            .thenApply(result -> result.map(saved ->
                                    OperationResult.of(
                                            "Đăng ký người dùng thành công",
                                            HelperMap.INSTANCE.userToDTO(saved)
                                    )
                            ));
                });
    }

    /**
     * Cập nhật thông tin người dùng.
     */
    public CompletionStage<Either<ExceptionMessage, OperationResult>> updateUser(UpdateUser command) {
        // Kiểm tra user có tồn tại không
        return userRepository.existsByUserId(command.getUserId())
                .thenCompose(exists -> {
                    if (!exists) {
                        return CompletableFuture.completedFuture(
                                Either.left(new ExceptionMessage(
                                        ErrorCode.USER_NOT_FOUND,
                                        "Không tìm thấy người dùng có ID: " + command.getUserId()
                                ))
                        );
                    }

                    // Tạo đối tượng mới với thông tin cập nhật
                    UserEntity user = new UserEntity(
                            command.getUserId(),
                            command.getFullName(),
                            command.getUsername(),
                            command.getEmail(),
                            command.getPhoneNumber(),
                            command.getAvatarUrl(),
                            command.getGender(),
                            command.getDateOfBirth(),
                            command.getAddress()
                    );

                    return userRepository.updateUser(user)
                            .thenApply(result -> result.map(v ->
                                    OperationResult.of("Cập nhật người dùng thành công", null)
                            ));
                });
    }

    /**
     * Lấy thông tin người dùng theo ID.
     */
    public CompletionStage<Either<ExceptionMessage, OperationResult>> getUserById(GetUserById command) {
        return userRepository.findById(command.getUserId())
                .thenApply(optionalUser -> optionalUser
                        .<Either<ExceptionMessage, OperationResult>>map(user ->
                                Either.right(OperationResult.of(
                                        "Lấy thông tin người dùng thành công",
                                       HelperMap.INSTANCE.userToDTO(user)
                                ))
                        )
                        .orElseGet(() ->
                                Either.left(new ExceptionMessage(
                                        ErrorCode.USER_NOT_FOUND,
                                        "Không tìm thấy người dùng có ID: " + command.getUserId()
                                ))
                        )
                );
    }
}
