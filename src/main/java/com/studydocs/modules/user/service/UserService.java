package com.studydocs.modules.user.service;

import com.studydocs.modules.user.dto.LoginRequest;
import com.studydocs.modules.user.dto.UserDto;
import com.studydocs.modules.user.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserDto getUserById(String userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(String userId, LoginRequest.UpdateUser request);
    UserDto updateAvatar(String userId, MultipartFile file);
    void deleteUser(String userId);
    UserDto toUserDto(UserEntity user);
}
