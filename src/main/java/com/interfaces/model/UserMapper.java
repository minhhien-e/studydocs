package com.interfaces.model;

import com.domain.dto.UserDTO;

public class UserMapper {

    public static UserDTO toDTO(RegisterRequest request) {
        return UserDTO.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .avatarUrl(request.getAvatarUrl())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .address(request.getAddress())
                .build();
    }
}