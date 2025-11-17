package com.domain.service;

import com.domain.command.*;
import com.domain.entity.UserEntity;
import com.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDomainServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserDomainService userDomainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDomainService = new UserDomainService(userRepository);
    }

    @Test
    void registerUser_success() {
        RegisterUser command = RegisterUser.commandOf(
                "John Doe", "johndoe", "john@example.com", "123456789",
                "/avatar.jpg", "Male", LocalDate.of(1990, 1, 1), "123 Street"
        );

        when(userRepository.existsByUsername("johndoe")).thenReturn(false);

        UserEntity savedUser = new UserEntity("1", "John Doe", "johndoe",
                "john@example.com", "123456789", "/avatar.jpg",
                "Male", LocalDate.of(1990,1,1), "123 Street");

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserEntity result = userDomainService.registerUser(command);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
        verify(userRepository, times(1)).existsByUsername("johndoe");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void registerUser_usernameExists_shouldThrow() {
        RegisterUser command = RegisterUser.commandOf(
                "John Doe", "johndoe", "john@example.com", "123456789",
                "/avatar.jpg", "Male", LocalDate.of(1990, 1, 1), "123 Street"
        );

        when(userRepository.existsByUsername("johndoe")).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                userDomainService.registerUser(command)
        );

        assertTrue(ex.getMessage().contains("Username 'johndoe' đã tồn tại"));
        verify(userRepository, never()).save(any());
    }

    @Test
    void updateUser_success() {
        UpdateUser command = UpdateUser.commandOf(
                "1", "Jane Doe", "janedoe", "jane@example.com",
                "987654321", "/avatar2.jpg", "Female", LocalDate.of(1992, 2, 2), "456 Street"
        );

        UserEntity existingUser = new UserEntity("1", "Old Name", "olduser", "old@example.com",
                "111222333", "/old.jpg", "Male", LocalDate.of(1990,1,1), "Old Street");

        when(userRepository.findById("1")).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        UserEntity result = userDomainService.updateUser(command);

        assertEquals("Jane Doe", result.getFullName());
        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void updateUser_userNotFound_shouldThrow() {
        UpdateUser command = UpdateUser.commandOf(
                "1", "Jane Doe", "janedoe", "jane@example.com",
                "987654321", "/avatar2.jpg", "Female", LocalDate.of(1992, 2, 2), "456 Street"
        );

        when(userRepository.findById("1")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                userDomainService.updateUser(command)
        );

        assertTrue(ex.getMessage().contains("Không tìm thấy người dùng có ID: 1"));
        verify(userRepository, never()).save(any());
    }

    @Test
    void getUserById_success() {
        GetUserById command = GetUserById.commandOf("1");

        UserEntity existingUser = new UserEntity("1", "John Doe", "johndoe",
                "john@example.com", "123456789", "/avatar.jpg",
                "Male", LocalDate.of(1990,1,1), "123 Street");

        when(userRepository.findById("1")).thenReturn(Optional.of(existingUser));

        UserEntity result = userDomainService.getUserById(command);

        assertEquals("John Doe", result.getFullName());
    }

    @Test
    void getUserById_notFound_shouldThrow() {
        GetUserById command = GetUserById.commandOf("1");
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                userDomainService.getUserById(command)
        );

        assertTrue(ex.getMessage().contains("Không tìm thấy người dùng có ID: 1"));
    }

    @Test
    void checkUserExist_true() {
        CheckUserExists command = CheckUserExists.commandOf("1");
        when(userRepository.existsByUserId("1")).thenReturn(true);

        boolean result = userDomainService.checkUserExist(command);
        assertTrue(result);
    }

    @Test
    void checkUserPrivate_true() {
        CheckUserPrivate command = CheckUserPrivate.commandOf("1");

        UserEntity user = new UserEntity("1", "John", "john", "john@example.com",
                "123", "/avatar", "Male", LocalDate.now(), "addr");
        user.setIsprivate(true);

        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        boolean result = userDomainService.checkUserPrivate(command);
        assertTrue(result);
    }

    @Test
    void updateImage_success() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("avatar.jpg");
        when(file.isEmpty()).thenReturn(false);

        UserEntity user = new UserEntity("1", "John", "john", "john@example.com",
                "123", "/old.jpg", "Male", LocalDate.now(), "addr");

        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity result = userDomainService.updateImage("1", file);

        assertEquals("/uploads/avatar.jpg", result.getAvatarUrl());
    }

    @Test
    void updateImage_fileEmpty_shouldThrow() {
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(true);

        UserEntity user = new UserEntity();
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                userDomainService.updateImage("1", file)
        );

        assertTrue(ex.getMessage().contains("Ảnh tải lên không hợp lệ"));
    }

    @Test
    void updateImage_userNotFound_shouldThrow() {
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);

        when(userRepository.findById("1")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                userDomainService.updateImage("1", file)
        );

        assertTrue(ex.getMessage().contains("Không tìm thấy người dùng có ID: 1"));
    }
}
