package com.example.authservice.repository;

import com.example.authservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Tìm user theo email
     * @param email Email cần tìm
     * @return User nếu tìm thấy
     */
    User findByEmail(String email);

    /**
     * Kiểm tra email đã tồn tại chưa
     * @param email Email cần kiểm tra
     * @return true nếu email đã tồn tại
     */
    boolean existsByEmail(String email);

    /**
     * Tìm user theo providerId và provider
     * @param providerId ID từ OAuth provider
     * @param provider Loại provider (GOOGLE, FACEBOOK, etc.)
     * @return User nếu tìm thấy
     */
    User findByProviderIdAndProvider(String providerId, User.AuthProvider provider);
} 