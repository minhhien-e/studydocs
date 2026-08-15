package com.studydocs.modules.user.controller;

import com.studydocs.modules.user.dto.LoginRequest;
import com.studydocs.modules.user.dto.UserDto;
import com.studydocs.modules.user.service.UserService;
import com.studydocs.shared.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<UserDto> getMyProfile(Authentication authentication) {
        String userId = authentication.getName();
        return ApiResponse.success(userService.getUserById(userId));
    }

    @GetMapping
    public ApiResponse<List<UserDto>> getAllUsers() {
        return ApiResponse.success(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserDto> getUserById(@PathVariable String userId) {
        return ApiResponse.success(userService.getUserById(userId));
    }

    @GetMapping("/{userId}/other")
    public ApiResponse<UserDto> getOtherUserProfile(@PathVariable String userId) {
        return ApiResponse.success(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserDto> updateUser(@PathVariable String userId, @Valid @RequestBody LoginRequest.UpdateUser request) {
        return ApiResponse.success(userService.updateUser(userId, request));
    }

    @PutMapping("/{userId}/info")
    public ApiResponse<UserDto> updateUserInfo(@PathVariable String userId, @Valid @RequestBody LoginRequest.UpdateUser request) {
        return ApiResponse.success(userService.updateUser(userId, request));
    }

    @PostMapping("/{userId}/image")
    public ApiResponse<UserDto> updateUserImage(@PathVariable String userId, @RequestParam("file") MultipartFile file) {
        return ApiResponse.success(userService.updateAvatar(userId, file));
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.success("User deleted successfully");
    }
}
