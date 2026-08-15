package com.studydocs.modules.user.service.impl;

import com.studydocs.infras.storage.FileStorageService;
import com.studydocs.modules.user.dto.LoginRequest;
import com.studydocs.modules.user.dto.UserDto;
import com.studydocs.modules.user.entity.UserEntity;
import com.studydocs.modules.user.repository.UserRepository;
import com.studydocs.modules.user.service.UserService;
import com.studydocs.shared.exception.AppException;
import com.studydocs.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(String userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return toUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toUserDto)
                .toList();
    }

    @Override
    @Transactional
    public UserDto updateUser(String userId, LoginRequest.UpdateUser request) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getBio() != null) user.setBio(request.getBio());
        if (request.getUniversityId() != null) user.setUniversityId(request.getUniversityId());
        if (request.getUniversityName() != null) user.setUniversityName(request.getUniversityName());
        if (request.getFacultyId() != null) user.setFacultyId(request.getFacultyId());
        if (request.getMajor() != null) user.setMajor(request.getMajor());
        if (request.getIsPrivate() != null) user.setIsPrivate(request.getIsPrivate());

        UserEntity updated = userRepository.save(user);
        return toUserDto(updated);
    }

    @Override
    @Transactional
    public UserDto updateAvatar(String userId, MultipartFile file) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        String avatarPath = fileStorageService.storeFile(file, "avatars");
        user.setAvatarUrl(avatarPath);

        UserEntity updated = userRepository.save(user);
        return toUserDto(updated);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto toUserDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .bio(user.getBio())
                .universityId(user.getUniversityId())
                .universityName(user.getUniversityName())
                .facultyId(user.getFacultyId())
                .major(user.getMajor())
                .isPrivate(user.getIsPrivate())
                .followerCount(0)
                .followingCount(0)
                .documentCount(0)
                .build();
    }
}
