package com.helper;

import com.domain.dto.UserDTO;
import com.domain.entity.UserEntity;

public enum HelperMap {

    INSTANCE; // ✅ duy nhất một instance

    // ⚙️ Hàm map từ Entity → DTO
    public UserDTO userToDTO(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return UserDTO.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .avatarUrl(entity.getAvatarUrl())
                .gender(entity.getGender())
                .dateOfBirth(entity.getDateOfBirth())
                .address(entity.getAddress())
                .build();
    }

    // ⚙️ Hàm map từ DTO → Entity
    public UserEntity dtoToUser(UserDTO dto) {
        if (dto == null) {
            return null;
        }

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
