package com.example.authservicev2.controller;

import com.example.authservicev2.domain.model.response.DetailUserResponse;
import com.example.authservicev2.domain.model.response.UserResponse;
import com.example.authservicev2.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user_v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<DetailUserResponse> me(@AuthenticationPrincipal Jwt jwt) {
        // Dùng sub (hoặc uid) để xác định user
        Long userId = Long.valueOf(jwt.getClaim("uid").toString());
        return ResponseEntity.ok(userService.getMe(userId));
    }
}