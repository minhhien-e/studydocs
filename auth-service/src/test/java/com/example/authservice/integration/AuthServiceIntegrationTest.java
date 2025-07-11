package com.example.authservice.integration;

import com.example.authservice.TestcontainersConfiguration;
import com.example.authservice.model.dto.request.RegisterRequest;
import com.example.authservice.model.dto.response.UserResponse;
import com.example.authservice.exception.ValidationException;
import com.example.authservice.model.entity.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@Transactional
class AuthServiceIntegrationTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    private RegisterRequest validRequest;

    @BeforeEach
    void setUp() {
        // Xóa dữ liệu test cũ
        userRepository.deleteAll();

        // Tạo request hợp lệ cho test
        validRequest = RegisterRequest.builder()
                .email("test@example.com")
                .userName("testuser")
                .password("Test@123")
                .confirmPassword("Test@123")
                .build();
    }

    @Test
    @DisplayName("Đăng ký thành công với dữ liệu hợp lệ")
    void registerSuccess() {
        // When
        UserResponse response = authService.register(validRequest);

        // Then
        assertNotNull(response);
        assertEquals(validRequest.getEmail(), response.getEmail());
        assertEquals(validRequest.getUserName(), response.getUserName());
        assertEquals(User.AuthProvider.LOCAL, response.getProvider());
        assertEquals(User.UserStatus.ACTIVE, response.getStatus());

        // Verify in database
        User savedUser = userRepository.findByEmail(validRequest.getEmail());
        assertNotNull(savedUser);
        assertEquals(validRequest.getUserName(), savedUser.getUserName());
        assertNotNull(savedUser.getPassword()); // Password should be encoded
        assertNotEquals(validRequest.getPassword(), savedUser.getPassword()); // Password should not be plain text
    }

    @Test
    @DisplayName("Đăng ký thất bại - Email đã tồn tại")
    void registerFailEmailExists() {
        // Given
        authService.register(validRequest); // Register first user

        // When & Then
        assertThrows(ValidationException.class, () -> {
            authService.register(validRequest); // Try to register with same email
        });

        // Verify only one user exists
        assertEquals(1, userRepository.count());
    }

    @Test
    @DisplayName("Đăng ký thất bại - Email không hợp lệ")
    void registerFailInvalidEmail() {
        // Given
        validRequest.setEmail("invalid-email");

        // When & Then
        assertThrows(ValidationException.class, () -> {
            authService.register(validRequest);
        });

        // Verify no user was created
        assertEquals(0, userRepository.count());
    }

    @Test
    @DisplayName("Đăng ký thất bại - Password yếu")
    void registerFailWeakPassword() {
        // Given
        validRequest.setPassword("weak");
        validRequest.setConfirmPassword("weak");

        // When & Then
        assertThrows(ValidationException.class, () -> {
            authService.register(validRequest);
        });

        // Verify no user was created
        assertEquals(0, userRepository.count());
    }

    @Test
    @DisplayName("Đăng ký thất bại - Password không khớp")
    void registerFailPasswordMismatch() {
        // Given
        validRequest.setConfirmPassword("Test@1234");

        // When & Then
        assertThrows(ValidationException.class, () -> {
            authService.register(validRequest);
        });

        // Verify no user was created
        assertEquals(0, userRepository.count());
    }

    @Test
    @DisplayName("Đăng ký nhiều user thành công")
    void registerMultipleUsersSuccess() {
        // Given
        RegisterRequest secondUser = RegisterRequest.builder()
                .email("test2@example.com")
                .userName("testuser2")
                .password("Test@123")
                .confirmPassword("Test@123")
                .build();

        // When
        UserResponse response1 = authService.register(validRequest);
        UserResponse response2 = authService.register(secondUser);

        // Then
        assertNotNull(response1);
        assertNotNull(response2);
        assertEquals(2, userRepository.count());
        
        // Verify users are different
        assertNotEquals(response1.getId(), response2.getId());
        assertNotEquals(response1.getEmail(), response2.getEmail());
    }
} 