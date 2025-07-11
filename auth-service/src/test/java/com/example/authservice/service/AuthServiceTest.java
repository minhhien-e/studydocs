package com.example.authservice.service;

import com.example.authservice.model.dto.request.RegisterRequest;
import com.example.authservice.model.dto.response.UserResponse;
import com.example.authservice.exception.ValidationException;
import com.example.authservice.model.entity.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    private RegisterRequest validRequest;
    private User mockUser;

    @BeforeEach
    void setUp() {
        // Chuẩn bị dữ liệu test hợp lệ
        validRequest = RegisterRequest.builder()
                .email("test@example.com")
                .userName("testuser")
                .password("Test@123")
                .confirmPassword("Test@123")
                .build();

        // Chuẩn bị mock user
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail(validRequest.getEmail());
        mockUser.setUserName(validRequest.getUserName());
        mockUser.setPassword("encodedPassword");
        mockUser.setProvider(User.AuthProvider.LOCAL);
        mockUser.setStatus(User.UserStatus.ACTIVE);
    }

    @Test
    @DisplayName("Đăng ký thành công với dữ liệu hợp lệ")
    void registerSuccess() {
        // Given
        when(userRepository.existsByEmail(validRequest.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // When
        UserResponse response = authService.register(validRequest);

        // Then
        assertNotNull(response);
        assertEquals(validRequest.getEmail(), response.getEmail());
        assertEquals(validRequest.getUserName(), response.getUserName());
        assertEquals(User.AuthProvider.LOCAL, response.getProvider());
        assertEquals(User.UserStatus.ACTIVE, response.getStatus());

        // Verify
        verify(userRepository).existsByEmail(validRequest.getEmail());
        verify(passwordEncoder).encode(validRequest.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Đăng ký thất bại - Email đã tồn tại")
    void registerFailEmailExists() {
        // Given
        when(userRepository.existsByEmail(validRequest.getEmail())).thenReturn(true);

        // When & Then
        ValidationException exception = assertThrows(
            ValidationException.class,
            () -> authService.register(validRequest)
        );

        assertEquals("Email đã được sử dụng", exception.getMessage());
        verify(userRepository).existsByEmail(validRequest.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Đăng ký thất bại - Email không hợp lệ")
    void registerFailInvalidEmail() {
        // Given
        validRequest.setEmail("invalid-email");

        // When & Then
        ValidationException exception = assertThrows(
            ValidationException.class,
            () -> authService.register(validRequest)
        );

        assertEquals("Email không đúng định dạng", exception.getMessage());
        verify(userRepository, never()).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Đăng ký thất bại - Password yếu")
    void registerFailWeakPassword() {
        // Given
        validRequest.setPassword("weak");
        validRequest.setConfirmPassword("weak");

        // When & Then
        ValidationException exception = assertThrows(
            ValidationException.class,
            () -> authService.register(validRequest)
        );

        assertTrue(exception.getMessage().contains("Mật khẩu phải có ít nhất 8 ký tự"));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Đăng ký thất bại - Password không khớp")
    void registerFailPasswordMismatch() {
        // Given
        validRequest.setConfirmPassword("Test@1234");

        // When & Then
        ValidationException exception = assertThrows(
            ValidationException.class,
            () -> authService.register(validRequest)
        );

        assertEquals("Mật khẩu xác nhận không khớp", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Đăng ký thất bại - Username trống")
    void registerFailEmptyUsername() {
        // Given
        validRequest.setUserName("");

        // When & Then
        ValidationException exception = assertThrows(
            ValidationException.class,
            () -> authService.register(validRequest)
        );

        assertTrue(exception.getMessage().contains("Tên người dùng không được để trống"));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Đăng ký thất bại - Email trống")
    void registerFailEmptyEmail() {
        // Given
        validRequest.setEmail("");

        // When & Then
        ValidationException exception = assertThrows(
            ValidationException.class,
            () -> authService.register(validRequest)
        );

        assertTrue(exception.getMessage().contains("Email không được để trống"));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Đăng ký thất bại - Password trống")
    void registerFailEmptyPassword() {
        // Given
        validRequest.setPassword("");
        validRequest.setConfirmPassword("");

        // When & Then
        ValidationException exception = assertThrows(
            ValidationException.class,
            () -> authService.register(validRequest)
        );

        assertTrue(exception.getMessage().contains("Mật khẩu không được để trống"));
        verify(userRepository, never()).save(any(User.class));
    }
} 