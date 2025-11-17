package com.domain.service;

import com.domain.command.*;
import com.domain.entity.UserEntity;
import com.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import static com.error.factory.ExceptionFactory.*;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    /**
     * Đăng ký người dùng mới.
     */
    public UserEntity registerUser(RegisterUser command) {
        boolean exists = userRepository.existsByUsername(command.getUsername());
        if (exists) {
            throw userAlreadyExists("registerUser");
        }

        UserEntity user = new UserEntity(
                null,
                command.getFullName(),
                command.getUsername(),
                command.getEmail(),
                command.getPhoneNumber(),
                command.getAvatarUrl(),
                command.getGender(),
                command.getDateOfBirth(),
                command.getAddress()
        );

        return userRepository.save(user);
    }

    /**
     * Cập nhật thông tin người dùng.
     */
    public UserEntity updateUser(UpdateUser command) {
        UserEntity existingUser = userRepository.findById(command.getUserId())
                .orElseThrow(() -> userNotFound("updateUser"));

        existingUser.setFullName(command.getFullName());
        existingUser.setUsername(command.getUsername());
        existingUser.setEmail(command.getEmail());
        existingUser.setPhoneNumber(command.getPhoneNumber());
        existingUser.setAvatarUrl(command.getAvatarUrl());
        existingUser.setGender(command.getGender());
        existingUser.setDateOfBirth(command.getDateOfBirth());
        existingUser.setAddress(command.getAddress());

        return userRepository.save(existingUser);
    }

    /**
     * Lấy thông tin người dùng theo ID.
     */
    public UserEntity getUserById(GetUserById command) {
        return userRepository.findById(command.getUserId())
                .orElseThrow(() -> userNotFound("getUserById"));
    }

    /**
     * Kiểm tra người dùng có tồn tại không.
     */
    public boolean checkUserExist(CheckUserExists command) {
        return userRepository.existsByUserId(command.getUserId());
    }

    /**
     * Kiểm tra người dùng có private không.
     */
    public boolean checkUserPrivate(CheckUserPrivate command) {
        UserEntity user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> userNotFound("checkUserPrivate"));

        return user.isIsprivate();
    }

    /**
     * Cập nhật ảnh đại diện của người dùng.
     */
    public UserEntity updateImage(String userId, MultipartFile image) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> userNotFound("updateImage"));

        if (image == null || image.isEmpty()) {
            throw invalidImage("updateImage");
        }

        try {
            String newAvatarUrl = "/uploads/" + image.getOriginalFilename();
            user.setAvatarUrl(newAvatarUrl);

            return userRepository.save(user);

        } catch (Throwable t) {
            throw internalError("updateImage");
        }
    }
}
