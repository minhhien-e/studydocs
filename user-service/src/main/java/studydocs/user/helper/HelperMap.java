package studydocs.user.helper;

import studydocs.user.domain.command.*;
import studydocs.user.domain.dto.UserDTO;
import studydocs.user.domain.entity.UserEntity;
import studydocs.user.interfaces.model.RegisterRequest;
import studydocs.user.interfaces.model.UpdateUserRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public enum HelperMap {

    INSTANCE; //

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
                .avatarUrl(entity.getAvatarID())
                .gender(entity.getGender())
                .dateOfBirth(entity.getDateOfBirth())
                .address(entity.getAddress())
                .school(entity.getSchool())
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
    public UserDTO registerRequesttoUserDTO(RegisterRequest request) {
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
    public RegisterUser toRegisterUser(RegisterRequest request) {
        return RegisterUser.commandOf(
                request.getFullName(),
                request.getUsername(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getAvatarUrl(),
                request.getGender(),
                request.getDateOfBirth(),
                request.getAddress()
        );
    }

    public UpdateUser toUpdateUser(UpdateUserRequest request, UUID userId) {
        return UpdateUser.commandOf(
                userId,
                request.getFullName(),
                request.getUsername(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getAvatarUrl(),
                request.getGender(),
                request.getDateOfBirth(),
                request.getAddress(),
                request.getSchool()
        );
    }
    public UpdateImage toUpdateImage(UUID userId, MultipartFile file) {
        return UpdateImage.commandOf(userId, file);
    }

    public GetUserById toGetUserById(UUID userId) {
        return GetUserById.commandOf(userId);
    }

    public CheckUserPrivate toCheckUserPrivate(UUID userId) {
        return CheckUserPrivate.commandOf(userId);
    }

    public CheckUserExists toCheckUserExists(UUID userId) {
        return CheckUserExists.commandOf(userId);
    }
}
