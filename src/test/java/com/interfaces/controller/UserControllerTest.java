package com.interfaces.controller;

import com.application.ManageUserService;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.RegisterRequest;
import com.interfaces.model.UpdateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class UserControllerTest {

    private ManageUserService manageUserService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        manageUserService = Mockito.mock(ManageUserService.class);
        userController = new UserController(manageUserService);
    }

    @Test
    void testRegister() {
        RegisterRequest request = new RegisterRequest();
        request.setFullName("John Doe");
        request.setUsername("johndoe");

        ApiResponse<?> expected = ApiResponse.success("User Registered", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.registerUser(any(RegisterRequest.class), anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.register(request, "trace123");

        assertNotNull(result);
        assertEquals("User Registered", result.data());
        assertEquals("Thành công", result.message());
    }

    @Test
    void testUpdateUser() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setId("u1");
        request.setFullName("Jane");

        ApiResponse<?> expected = ApiResponse.success("User Updated", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.updateUser(any(UpdateUserRequest.class), anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.update(request, "trace123");

        assertEquals("User Updated", result.data());
        assertEquals("Thành công", result.message());
    }

    @Test
    void testGetUserByID() {
        ApiResponse<?> expected = ApiResponse.success("User Data", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.getUserById(anyString(), anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.getUserByID("u1", "trace123");

        assertEquals("User Data", result.data());
        assertEquals("Thành công", result.message());
    }

    @Test
    void testCheckUserPrivate() {
        ApiResponse<?> expected = ApiResponse.success(true, "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.isUserPrivate(anyString(), anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.checkUserPrivate("u1", "trace123");

        assertEquals(true, result.data());
    }

    @Test
    void testCheckUserExists() {
        ApiResponse<?> expected = ApiResponse.success(true, "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.isUserExists(anyString(), anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.checkUserExists("u1", "trace123");

        assertEquals(true, result.data());
    }

    @Test
    void testUpdateImage() {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "avatar.png",
                "image/png",
                "dummy".getBytes()
        );

        ApiResponse<?> expected = ApiResponse.success("/uploads/avatar.png", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.updateImage(anyString(), any(MultipartFile.class), anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.updateImage("u1", file, "trace123");

        assertEquals("/uploads/avatar.png", result.data());
    }

    @Test
    void testGetAllUsers() {
        ApiResponse<?> expected = ApiResponse.success("All Users", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.getAllUsers(anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.getAllUsers("trace123");

        assertEquals("All Users", result.data());
    }

    @Test
    void testGetUserCount() {
        ApiResponse<?> expected = ApiResponse.success(10, "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.getUserCount(anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.getUserCount("trace123");

        assertEquals(10, result.data());
    }

    @Test
    void testDeleteUser() {
        ApiResponse<?> expected = ApiResponse.success("Deleted", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.deleteUser(anyString(), anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.deleteUser("u1", "trace123");

        assertEquals("Deleted", result.data());
    }

    @Test
    void testGetUsersInRange() {
        ApiResponse<?> expected = ApiResponse.success("Users Range", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.getUsersInRange(anyInt(), anyInt(), anyString()))
                .thenReturn(expected);

        ApiResponse<?> result = userController.getUsersInRange(0, 5, "trace123");

        assertEquals("Users Range", result.data());
    }
}
