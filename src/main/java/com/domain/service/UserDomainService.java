package com.domain.service;

import com.domain.dto.UserDTO;
import com.domain.entity.UserEntity;
import com.domain.exception.ExceptionFactory;
import com.domain.exception.ExceptionMessage;
import com.domain.repository.UserRepository;
import com.domain.result.OperationResult;
import com.domain.result.ResultHelper;

import io.github.resilience4j.core.functions.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletionStage;

@Service
public class UserDomainService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDomainService.class);

    private final UserRepository userRepository;
    private final ExceptionFactory exceptionFactory;

    public UserDomainService(UserRepository userRepository, ExceptionFactory exceptionFactory) {
        this.userRepository = userRepository;
        this.exceptionFactory = exceptionFactory;
    }

    /**
     * Đăng ký người dùng mới
     */
    public CompletionStage<Either<ExceptionMessage, OperationResult>> register(UserDTO dto) {
        return userRepository.existsByUsername(dto.getUsername())
                .thenCompose(exists -> {
                    if (exists) {
                        return ResultHelper.failure(exceptionFactory.badRequest(
                                "Username already exists",
                                "Người dùng đã tồn tại với username: " + dto.getUsername()
                        ));
                    }

                    UserEntity user = mapToEntity(dto);
                    return userRepository.save(user)
                            .thenCompose(saved ->
                                    ResultHelper.success("Đăng ký thành công", mapToDTO(saved.get()))
                            );
                })
                .exceptionally(e -> {
                    LOG.error("Error in register user", e);
                    return Either.left(exceptionFactory.systemError(e.getMessage()));
                });
    }

    /**
     * Cập nhật thông tin người dùng
     */
    public CompletionStage<Either<ExceptionMessage, OperationResult>> update(UserDTO dto) {
        if (!validate(dto)) {
            return ResultHelper.failure(exceptionFactory.badRequest(
                    "Invalid input",
                    "Dữ liệu đầu vào không hợp lệ"
            ));
        }

        return userRepository.existsByUserId(dto.getId())
                .thenCompose(exists -> {
                    if (!exists) {
                        return ResultHelper.failure(exceptionFactory.notFound(
                                "User not found",
                                "User với ID " + dto.getId() + " không tồn tại"
                        ));
                    }

                    UserEntity user = mapToEntity(dto);
                    return userRepository.updateUser(user)
                            .thenCompose(v ->
                                    ResultHelper.success("Cập nhật thành công", mapToDTO(user))
                            );
                })
                .exceptionally(e -> {
                    LOG.error("Error in update user", e);
                    return Either.left(exceptionFactory.systemError(e.getMessage()));
                });
    }

    /**
     * Lấy thông tin người dùng
     */
    public CompletionStage<Either<ExceptionMessage, OperationResult>> getUser(String id) {
        return userRepository.existsByUserId(id)
                .thenCompose(exists -> {
                    if (!exists) {
                        return ResultHelper.failure(exceptionFactory.notFound(
                                "User not found",
                                "Người dùng với ID " + id + " không tồn tại"
                        ));
                    }

                    return userRepository.findById(id)
                            .thenCompose(optionalUser ->
                                    optionalUser.<CompletionStage<Either<ExceptionMessage, OperationResult>>>map(
                                                    user -> ResultHelper.success("Lấy thông tin thành công", mapToDTO(user))
                                            )
                                            .orElseGet(() -> ResultHelper.failure(exceptionFactory.notFound(
                                                    "User not found",
                                                    "Không tìm thấy thông tin người dùng"
                                            )))
                            );
                })
                .exceptionally(ex -> {
                    LOG.error("Error in get user", ex);
                    return Either.left(exceptionFactory.systemError(ex.getMessage()));
                });
    }

    // ----------------- Helper -----------------

    private boolean validate(UserDTO dto) {
        return dto != null &&
                dto.getUsername() != null &&
                !dto.getUsername().isBlank() &&
                dto.getEmail() != null &&
                dto.getEmail().contains("@");
    }

    private UserDTO mapToDTO(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAvatarUrl(),
                user.getGender(),
                user.getDateOfBirth(),
                user.getAddress()
        );
    }

    private UserEntity mapToEntity(UserDTO dto) {
        return new UserEntity(
                dto.getId(),
                dto.getFullName(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getAvatarUrl(),
                dto.getGender(),
                dto.getDateOfBirth(),
                dto.getAddress()
        );
    }
}
