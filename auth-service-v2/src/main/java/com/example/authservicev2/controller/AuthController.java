package com.example.authservicev2.controller;

import com.example.authservicev2.domain.model.request.LocalRegisterRequest;
import com.example.authservicev2.domain.model.request.LocalLoginRequest;
import com.example.authservicev2.domain.model.request.LoginRequest;
import com.example.authservicev2.domain.model.request.RegisterRequest;
import com.example.authservicev2.domain.model.response.TokenResponse;
import com.example.authservicev2.domain.model.response.UserResponse;
import com.example.authservicev2.service.interfaces.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth_v1")
public class AuthController {
    public final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


//    username, email, password
    @PostMapping("/register/local")
    public ResponseEntity<UserResponse> registerLocal(@Valid @RequestBody LocalRegisterRequest registerRequest) {
        UserResponse userResponse = authService.registerLocal(registerRequest);
        return ResponseEntity.ok(userResponse);
    }

//  provider, authorizationCode, redirectUri
    @PostMapping("/register/social")
    public ResponseEntity<UserResponse> registerSocial(@Valid @RequestBody RegisterRequest registerRequest) {
        UserResponse userResponse = authService.registerSocial(registerRequest);
        return ResponseEntity.ok(userResponse);
    }
// provider, authorizationCode, redirectUri
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authService.login(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    // username, password
    @PostMapping("/login/local")
    public ResponseEntity<TokenResponse> loginLocal(@Valid @RequestBody LocalLoginRequest request) {
        TokenResponse tokenResponse = authService.loginLocal(request);
        return ResponseEntity.ok(tokenResponse);
    }

    // refreshToken
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@Valid @RequestBody TokenResponse tokenResponse) {
        TokenResponse refreshedTokenResponse = authService.refreshToken(tokenResponse);
        return ResponseEntity.ok(refreshedTokenResponse);
    }
}
