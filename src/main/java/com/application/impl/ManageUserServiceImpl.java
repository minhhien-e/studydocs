package com.application.impl;

import com.application.ManageUserService;
import com.application.bus.SimpleUserCommandBus;
import com.domain.command.*;
import com.helper.HelperMap;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.RegisterRequest;
import com.interfaces.model.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Service
@Slf4j
public class ManageUserServiceImpl implements ManageUserService {

    private final SimpleUserCommandBus commandBus;

    @Override
    public ApiResponse<?> registerUser(RegisterRequest request, String traceId) {
        var command = HelperMap.INSTANCE.toRegisterUser(request);
        var result = commandBus.send(command); // DomainException sẽ được ném nếu lỗi
        return ApiResponse.success(result, null); // message = null
    }

    @Override
    public ApiResponse<?> updateUser(UpdateUserRequest request, String traceId) {
        var command = HelperMap.INSTANCE.toUpdateUser(request, request.getId());
        var result = commandBus.send(command);
        return ApiResponse.success(result, null);
    }

    @Override
    public ApiResponse<?> updateImage(String id, MultipartFile file, String traceId) {
        var command = HelperMap.INSTANCE.toUpdateImage(id, file);
        var result = commandBus.send(command);
        return ApiResponse.success(result, null);
    }

    @Override
    public ApiResponse<?> getUserById(String id, String traceId) {
        var command = HelperMap.INSTANCE.toGetUserById(id);
        var user = commandBus.send(command);
        return ApiResponse.success(user, null);
    }

    @Override
    public ApiResponse<?> isUserPrivate(String userId, String traceId) {
        var command = HelperMap.INSTANCE.toCheckUserPrivate(userId);
        var isPrivate = commandBus.send(command);
        return ApiResponse.success(isPrivate, null);
    }

    @Override
    public ApiResponse<?> isUserExists(String userId, String traceId) {
        var command = HelperMap.INSTANCE.toCheckUserExists(userId);
        var exists = commandBus.send(command);
        return ApiResponse.success(exists, null);
    }
}
