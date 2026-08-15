package com.studydocs.modules.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class LoginRequest {

    @Data
    public static class Login {
        @NotBlank(message = "Email/Username is required")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;
    }

    @Data
    public static class Register {
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;

        @NotBlank(message = "Full name is required")
        private String fullName;

        private String username;
        private Long universityId;
    }

    @Data
    public static class RefreshToken {
        @NotBlank(message = "RefreshToken is required")
        private String refreshToken;
    }

    @Data
    public static class UpdateUser {
        private String fullName;
        private String bio;
        private Long universityId;
        private String universityName;
        private Long facultyId;
        private String major;
        private Boolean isPrivate;
    }
}
