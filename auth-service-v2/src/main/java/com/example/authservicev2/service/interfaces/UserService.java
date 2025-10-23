package com.example.authservicev2.service.interfaces;

import com.example.authservicev2.domain.model.response.DetailUserResponse;
import com.example.authservicev2.domain.model.response.UserResponse;

public interface UserService {
    DetailUserResponse getMe(Long userId);
}
