package com.example.authservicev2.service.impl;

import com.example.authservicev2.domain.model.entities.Role;
import com.example.authservicev2.domain.model.entities.User;
import com.example.authservicev2.domain.model.response.DetailUserResponse;
import com.example.authservicev2.domain.repository.UserRepository;
import com.example.authservicev2.domain.repository.UserRolesRepository;
import com.example.authservicev2.exception.CustomExceptions;
import com.example.authservicev2.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository; // THÊM

    @Override
    public DetailUserResponse getMe(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.ValidationException("User not found with id: " + userId));

        // LẤY ROLES QUA USER_ROLES ENTITY
        List<Role> userRoles = userRolesRepository.findRolesByUserId(userId);

        DetailUserResponse res = new DetailUserResponse();
        res.setId(user.getId());
        res.setName(user.getUserName());
        res.setEmail(user.getEmail());
        res.setProvider(user.getProvider() != null ? user.getProvider().toString() : null);
        res.setStatus(user.getStatus());
        res.setRole(userRoles);
        return res;
    }
}