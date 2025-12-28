package com.domain.repository;

import com.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    /**
     * Kiểm tra username đã tồn tại chưa
     * @return true nếu tồn tại, false nếu không
     */
    boolean existsByUsername(String username);

    /**
     * Kiểm tra userId đã tồn tại chưa
     * @return true nếu tồn tại, false nếu không
     */
    boolean existsByUserId(String id);

    /**
     * Lưu mới user
     * @param user user entity cần lưu
     * @return UserEntity đã lưu
     * @throws ExceptionMessage nếu lưu thất bại
     */
    UserEntity save(UserEntity user);

    /**
     * Cập nhật user
     * @param user user entity cần cập nhật
     * @throws ExceptionMessage nếu user không tồn tại hoặc cập nhật thất bại
     */
    void updateUser(UserEntity user);

    /**
     * Xóa user theo id
     * @param id id của user
     * @throws ExceptionMessage nếu user không tồn tại hoặc xóa thất bại
     */
    void deleteById(String id);

    /**
     * Tìm user theo id
     * @param id id của user
     * @return Optional chứa UserEntity nếu tồn tại, empty nếu không
     * @throws ExceptionMessage nếu có lỗi truy vấn
     */
    Optional<UserEntity> findById(String id);

    /**
     * Tìm user theo username
     * @param username username của user
     * @return Optional chứa UserEntity nếu tồn tại, empty nếu không
     * @throws ExceptionMessage nếu có lỗi truy vấn
     */
    Optional<UserEntity> findByUsername(String username);
    // Lấy tất cả user
    List<UserEntity> findAll();

    // Kiểm tra user tồn tại theo ID
    boolean existsById(String id);

    // Đếm tổng số user
    long count();
}
