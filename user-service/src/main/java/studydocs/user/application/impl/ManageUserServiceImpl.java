package studydocs.user.application.impl;

import studydocs.user.application.ManageUserService;
import studydocs.user.application.bus.SimpleUserCommandBus;
import studydocs.user.domain.command.DeleteUser;
import studydocs.user.domain.command.GetAllUser;
import studydocs.user.domain.command.GetUserCount;
import studydocs.user.domain.command.GetUsersInRange;
import studydocs.user.helper.HelperMap;
import studydocs.user.infrastructure.JwtCurrentUserProvider;
import studydocs.user.interfaces.model.ApiResponse;
import studydocs.user.interfaces.model.RegisterRequest;
import studydocs.user.interfaces.model.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ManageUserServiceImpl implements ManageUserService {

    private final SimpleUserCommandBus commandBus;
    private final JwtCurrentUserProvider jwtCurrentUserProvider;
    @Override
    public ApiResponse<?> registerUser(RegisterRequest request, String traceId) {
        var command = HelperMap.INSTANCE.toRegisterUser(request);
        var result = commandBus.send(command);
        log.info(result.toString());
        return ApiResponse.success(result, null);
    }

    @Override
    public ApiResponse<?> updateUser(UpdateUserRequest request, String traceId) {
        var command = HelperMap.INSTANCE.toUpdateUser(request,   jwtCurrentUserProvider.getCurrentUserId());
        var result = commandBus.send(command);
        return ApiResponse.success(result, null);
    }

    @Override
    public ApiResponse<?> updateImage(UUID id, MultipartFile file, String traceId) {
        var command = HelperMap.INSTANCE.toUpdateImage(  jwtCurrentUserProvider.getCurrentUserId(), file);
        var result = commandBus.send(command);
        return ApiResponse.success(result, null);
    }

    @Override
    public ApiResponse<?> getUserById(UUID id, String traceId) {
        var command = HelperMap.INSTANCE.toGetUserById(id);
        var user = commandBus.send(command);
        return ApiResponse.success(user, null);
    }

    @Override
    public ApiResponse<?> isUserPrivate(UUID userId, String traceId) {
        var command = HelperMap.INSTANCE.toCheckUserPrivate(userId);
        var isPrivate = commandBus.send(command);
        return ApiResponse.success(isPrivate, null);
    }

    @Override
    public ApiResponse<?> isUserExists(UUID userId, String traceId) {
        var command = HelperMap.INSTANCE.toCheckUserExists(userId);
        var exists = commandBus.send(command);
        return ApiResponse.success(exists, null);
    }

    @Override
    public ApiResponse<?> getUserCount(String traceId) {
        var command = GetUserCount.commandOf();
        var count = commandBus.send(command);
        return ApiResponse.success(count, null);
    }

    @Override
    public ApiResponse<?> deleteUser(UUID id, String traceId) {
        var command = DeleteUser.commandOf(id);
        var result = commandBus.send(command);
        return ApiResponse.success(result, null);
    }

    @Override
    public ApiResponse<?> getUsersInRange(int fromIndex, int toIndex, String traceId) {
        var command = GetUsersInRange.commandOf(fromIndex, toIndex);
        var users = commandBus.send(command);
        return ApiResponse.success(users, null);
    }

    @Override
    public ApiResponse<?> getAllUsers(String traceId) {
        var command = GetAllUser.commandOf();
        var users = commandBus.send(command);
        return ApiResponse.success(users, null);
    }
}
