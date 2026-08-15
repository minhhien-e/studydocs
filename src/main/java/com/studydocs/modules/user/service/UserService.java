package com.studydocs.modules.user.service;

import com.studydocs.modules.user.dto.LoginRequest;
import com.studydocs.modules.user.dto.UserDto;
import com.studydocs.modules.user.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interface định nghĩa các hợp đồng nghiệp vụ quản lý người dùng và hồ sơ cá nhân.
 *
 * @author StudyDocs Team
 * @since 1.0.0
 */
public interface UserService {

    /**
     * Lấy thông tin chi tiết người dùng theo ID.
     *
     * @param userId ID người dùng
     * @return {@link UserDto} DTO chứa thông tin hồ sơ
     * @throws com.studydocs.shared.exception.AppException nếu người dùng không tồn tại
     */
    UserDto getUserById(String userId);

    /**
     * Lấy danh sách tất cả người dùng trong hệ thống.
     *
     * @return Danh sách các {@link UserDto}
     */
    List<UserDto> getAllUsers();

    /**
     * Cập nhật thông tin hồ sơ người dùng.
     *
     * @param userId ID người dùng cần cập nhật
     * @param request DTO chứa các thông tin thay đổi (họ tên, bio, trường, khoa, ngành...)
     * @return {@link UserDto} sau khi đã cập nhật thành công
     */
    UserDto updateUser(String userId, LoginRequest.UpdateUser request);

    /**
     * Cập nhật ảnh đại diện (Avatar) cho người dùng.
     *
     * @param userId ID người dùng
     * @param file File ảnh tải lên
     * @return {@link UserDto} với URL ảnh đại diện mới
     */
    UserDto updateAvatar(String userId, MultipartFile file);

    /**
     * Xóa tài khoản người dùng khỏi hệ thống.
     *
     * @param userId ID người dùng cần xóa
     */
    void deleteUser(String userId);

    /**
     * Chuyển đổi từ JPA Entity {@link UserEntity} sang DTO {@link UserDto}.
     *
     * @param user Entity người dùng
     * @return DTO người dùng tương ứng
     */
    UserDto toUserDto(UserEntity user);
}
