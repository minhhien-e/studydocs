package com.interfaces.controller;

import com.application.ManageUserService;
import com.domain.dto.UserDTO;
import com.domain.exception.ExceptionMessage;
import com.domain.result.OperationResult;
import com.interfaces.model.RegisterRequest;

import io.github.resilience4j.core.functions.Either;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ManageUserService manageUserService;

    @Test
    @DisplayName("POST /users/register - success")
    void testRegisterSuccess() throws Exception {
        OperationResult successResult = OperationResult.of("register success",
                new UserDTO("1", "Test User", "testuser", "test@example.com", 123456789, null, null, null, null));

        Mockito.when(manageUserService.registerUser(any(UserDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(Either.right(successResult)));

        RegisterRequest request = new RegisterRequest("Test User", "testuser", "test@example.com", 123456789, null, null, null, null);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.username").value("testuser"))
                .andExpect(jsonPath("$.message").value("register success"));
    }

    @Test
    @DisplayName("POST /users/register - fail")
    void testRegisterFail() throws Exception {
        ExceptionMessage error = new ExceptionMessage(400, "User already exists", "");
        Mockito.when(manageUserService.registerUser(any(UserDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(Either.left(error)));

        RegisterRequest request = new RegisterRequest("Test User", "testuser", "test@example.com", 123456789, null, null, null, null);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("User already exists"));
    }

    @Test
    @DisplayName("GET /users/getUserByID - success")
    void testGetUserByIdSuccess() throws Exception {
        UserDTO mockUser = new UserDTO("1", "Test User", "testuser", "test@example.com", 23456789, null, null, null, null);
        OperationResult successResult = OperationResult.of("Lấy thông tin người dùng thành công", mockUser);

        Mockito.when(manageUserService.getUserById("1"))
                .thenReturn(CompletableFuture.completedFuture(Either.right(successResult)));

        mockMvc.perform(get("/users/getUserByID")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Lấy thông tin người dùng thành công"))
                .andExpect(jsonPath("$.data.username").value("testuser"));
    }

    @Test
    @DisplayName("GET /users/getUserByID - not found")
    void testGetUserByIdNotFound() throws Exception {
        ExceptionMessage error = new ExceptionMessage(404, "User not found", "");
        Mockito.when(manageUserService.getUserById("99"))
                .thenReturn(CompletableFuture.completedFuture(Either.left(error)));

        mockMvc.perform(get("/users/getUserByID")
                        .param("id", "99"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("User not found"));
    }
}
