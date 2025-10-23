package com.example.authservice.model.dto.response;

import com.example.authservice.model.entity.User;
import java.time.LocalDateTime;
import java.util.Set;

import com.example.authservice.model.enums.AuthProvider;
import com.example.authservice.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String userName;
    private AuthProvider provider;
    private UserStatus status;
    private Set<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 