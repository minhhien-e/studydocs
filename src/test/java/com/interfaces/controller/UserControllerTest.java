package com.interfaces.controller;

import com.application.ManageUserService;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.RegisterRequest;
import com.interfaces.model.UpdateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

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

        ApiResponse<?> expectedResponse = ApiResponse.success("User Registered", "Thành công");

        // fix generic wildcard
        Mockito.<ApiResponse<?>>when(manageUserService.registerUser(any(RegisterRequest.class), anyString()))
                .thenReturn(expectedResponse);

        ApiResponse<?> response = userController.register(request, "trace123");

        assertNotNull(response);
        assertEquals("User Registered", response.data());
        assertEquals("Thành công", response.message());
    }

    @Test
    void testUpdateUser() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setId("user123");
        request.setFullName("Jane Doe");

        ApiResponse<?> expectedResponse = ApiResponse.success("User Updated", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.updateUser(any(UpdateUserRequest.class), anyString()))
                .thenReturn(expectedResponse);

        ApiResponse<?> response = userController.update(request, "trace123");

        assertNotNull(response);
        assertEquals("User Updated", response.data());
        assertEquals("Thành công", response.message());
    }

    @Test
    void testGetUserByID() {
        String userId = "user123";
        ApiResponse<?> expectedResponse = ApiResponse.success("User Data", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.getUserById(anyString(), anyString()))
                .thenReturn(expectedResponse);

        ApiResponse<?> response = userController.getUserByID(userId, "trace123");

        assertNotNull(response);
        assertEquals("User Data", response.data());
        assertEquals("Thành công", response.message());
    }

    @Test
    void testCheckUserPrivate() {
        String userId = "user123";
        ApiResponse<?> expectedResponse = ApiResponse.success(true, "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.isUserPrivate(anyString(), anyString()))
                .thenReturn(expectedResponse);

        ApiResponse<?> response = userController.checkUserPrivate(userId, "trace123");

        assertNotNull(response);
        assertEquals(true, response.data());
        assertEquals("Thành công", response.message());
    }

    @Test
    void testCheckUserExists() {
        String userId = "user123";
        ApiResponse<?> expectedResponse = ApiResponse.success(true, "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.isUserExists(anyString(), anyString()))
                .thenReturn(expectedResponse);

        ApiResponse<?> response = userController.checkUserExists(userId, "trace123");

        assertNotNull(response);
        assertEquals(true, response.data());
        assertEquals("Thành công", response.message());
    }

    @Test
    void testUpdateImage() {
        String userId = "user123";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "avatar.png",
                "image/png",
                "dummy".getBytes()
        );

        ApiResponse<?> expectedResponse = ApiResponse.success("/uploads/avatar.png", "Thành công");

        Mockito.<ApiResponse<?>>when(manageUserService.updateImage(anyString(), any(MockMultipartFile.class), anyString()))
                .thenReturn(expectedResponse);

        ApiResponse<?> response = userController.updateImage(userId, file, "trace123");

        assertNotNull(response);
        assertEquals("/uploads/avatar.png", response.data());
        assertEquals("Thành công", response.message());
    }
}
