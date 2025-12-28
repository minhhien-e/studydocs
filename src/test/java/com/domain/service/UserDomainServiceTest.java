package com.domain.service;

import com.domain.command.*;
import com.domain.entity.UserEntity;
import com.domain.repository.UserRepository;
import com.error.factory.ExceptionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDomainServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ImageServiceClient imageServiceClient;

    private UserDomainService userDomainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDomainService = new UserDomainService(userRepository, imageServiceClient);
    }

    // -------------------------------------------------------------
    // REGISTER USER
    // -------------------------------------------------------------

    @Test
    void registerUser_success() {

        RegisterUser command = RegisterUser.commandOf(
                "John Doe", "johndoe", "john@example.com", "123456789",
                "/avatar.jpg", "Male", LocalDate.of(1990, 1, 1), "123 Street"
        );

        when(userRepository.existsByUsername("johndoe")).thenReturn(false);

        UserEntity savedUser = new UserEntity(
                "1", "John Doe", "johndoe", "john@example.com",
                "123456789", "/avatar.jpg", "Male",
                LocalDate.of(1990,1,1), "123 Street"
        );

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserEntity result = userDomainService.registerUser(command);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void registerUser_usernameExists_shouldThrow() {

        RegisterUser command = RegisterUser.commandOf(
                "John Doe", "johndoe", "john@example.com", "123",
                "/avatar.jpg", "Male", LocalDate.now(), "addr"
        );

        when(userRepository.existsByUsername("johndoe")).thenReturn(true);

        assertThrows(RuntimeException.class,
                () -> userDomainService.registerUser(command));

        verify(userRepository, never()).save(any());
    }

    // -------------------------------------------------------------
    // UPDATE USER
    // -------------------------------------------------------------

    @Test
    void updateUser_success() {

        UpdateUser command = UpdateUser.commandOf(
                "1", "Jane Doe", "janedoe", "jane@example.com",
                "987654321", "/avatar2.jpg", "Female",
                LocalDate.of(1992, 2, 2), "456 Street"
        );

        UserEntity existing = new UserEntity(
                "1", "Old", "olduser", "old@example.com",
                "111", "/old.jpg", "Male", LocalDate.of(1990,1,1), "Old Street"
        );

        when(userRepository.findById("1")).thenReturn(Optional.of(existing));
        when(userRepository.save(any(UserEntity.class))).thenReturn(existing);

        UserEntity result = userDomainService.updateUser(command);

        assertEquals("Jane Doe", result.getFullName());
        assertEquals("janedoe", result.getUsername());
    }

    @Test
    void updateUser_userNotFound_shouldThrow() {
        UpdateUser command = UpdateUser.commandOf(
                "1", "Jane", "jane", "jane@example.com",
                "987", "/ava.jpg", "Female", LocalDate.now(), "addr"
        );

        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userDomainService.updateUser(command));
    }

    // -------------------------------------------------------------
    // GET USER BY ID
    // -------------------------------------------------------------

    @Test
    void getUserById_success() {
        GetUserById command = GetUserById.commandOf("1");

        UserEntity user = new UserEntity(
                "1", "John", "john", "john@example.com",
                "123", "/ava", "Male", LocalDate.now(), "addr"
        );

        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        UserEntity result = userDomainService.getUserById(command);

        assertEquals("John", result.getFullName());
    }

    @Test
    void getUserById_notFound_shouldThrow() {
        GetUserById command = GetUserById.commandOf("1");

        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userDomainService.getUserById(command));
    }

    // -------------------------------------------------------------
    // CHECK USER EXIST
    // -------------------------------------------------------------

    @Test
    void checkUserExist_true() {
        CheckUserExists cmd = CheckUserExists.commandOf("1");
        when(userRepository.existsByUserId("1")).thenReturn(true);

        assertTrue(userDomainService.checkUserExist(cmd));
    }

    // -------------------------------------------------------------
    // CHECK USER PRIVATE
    // -------------------------------------------------------------

    @Test
    void checkUserPrivate_true() {
        CheckUserPrivate cmd = CheckUserPrivate.commandOf("1");

        UserEntity user = new UserEntity();
        user.setIsprivate(true);

        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        assertTrue(userDomainService.checkUserPrivate(cmd));
    }

    @Test
    void checkUserPrivate_userNotFound_shouldThrow() {

        CheckUserPrivate cmd = CheckUserPrivate.commandOf("1");
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userDomainService.checkUserPrivate(cmd));
    }

    // -------------------------------------------------------------
    // UPDATE IMAGE
    // -------------------------------------------------------------

    @Test
    void updateImage_success() {

        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);
        when(imageServiceClient.uploadImage(file)).thenReturn("/uploads/avatar.jpg");

        UserEntity user = new UserEntity();
        user.setId("1");
        user.setAvatarUrl("/old.jpg");

        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity result = userDomainService.updateImage("1", file);

        assertEquals("/uploads/avatar.jpg", result.getAvatarUrl());
    }

    @Test
    void updateImage_invalidFile_shouldThrow() {

        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(true);

        UserEntity user = new UserEntity();
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class,
                () -> userDomainService.updateImage("1", file));
    }

    @Test
    void updateImage_userNotFound_shouldThrow() {

        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);

        when(userRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userDomainService.updateImage("1", file));
    }

    // -------------------------------------------------------------
    // GET ALL USERS
    // -------------------------------------------------------------

    @Test
    void getAllUsers_success() {

        List<UserEntity> users = Arrays.asList(
                new UserEntity(), new UserEntity()
        );

        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> result = userDomainService.getAllUsers();

        assertEquals(2, result.size());
    }

    // -------------------------------------------------------------
    // GET USERS IN RANGE
    // -------------------------------------------------------------

    @Test
    void getUsersInRange_success() {

        List<UserEntity> users = Arrays.asList(
                new UserEntity(), new UserEntity(), new UserEntity()
        );

        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> result = userDomainService.getUsersInRange(0, 1);

        assertEquals(2, result.size());
    }

    @Test
    void getUsersInRange_invalidRange_shouldThrow() {

        when(userRepository.findAll()).thenReturn(Arrays.asList(new UserEntity()));

        assertThrows(RuntimeException.class,
                () -> userDomainService.getUsersInRange(0, 5));
    }

    // -------------------------------------------------------------
    // DELETE USER
    // -------------------------------------------------------------

    @Test
    void deleteUser_success() {

        when(userRepository.existsById("1")).thenReturn(true);

        Boolean result = userDomainService.deleteUser("1");

        assertTrue(result);
        verify(userRepository).deleteById("1");
    }

    @Test
    void deleteUser_notFound_shouldThrow() {

        when(userRepository.existsById("1")).thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> userDomainService.deleteUser("1"));
    }

    // -------------------------------------------------------------
    // COUNT USERS
    // -------------------------------------------------------------

    @Test
    void getUserCount_success() {
        when(userRepository.count()).thenReturn(5L);

        Integer result = userDomainService.getUserCount();

        assertEquals(5, result);
    }
}
