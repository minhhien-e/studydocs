package com.example.authservice.repository;

import com.example.authservice.model.entity.User;
import com.example.authservice.model.enums.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Tìm user theo email
     * @param email Email cần tìm
     * @return Optional<User> chứa user nếu tìm thấy
     */
    Optional<User> findByEmail(String email);

    /**
     * Tìm user theo username
     * @param username Username cần tìm
     * @return Optional<User> chứa user nếu tìm thấy
     */
    Optional<User> findByUserName(String username);

    /**
     * Kiểm tra email đã tồn tại chưa
     * @param email Email cần kiểm tra
     * @return true nếu email đã tồn tại
     */
    boolean existsByEmail(String email);

    /**
     * Kiểm tra username đã tồn tại chưa
     * @param username Username cần kiểm tra
     * @return true nếu username đã tồn tại
     */
    boolean existsByUserName(String username);

    /**
     * Tìm user theo providerId và provider
     * @param providerId ID từ OAuth provider
     * @param provider Loại provider (GOOGLE, FACEBOOK, etc.)
     * @return Optional<User> chứa user nếu tìm thấy
     */
    Optional<User> findByProviderIdAndProvider(String providerId, AuthProvider provider);
}