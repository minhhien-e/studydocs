package com.application;

import com.interfaces.model.ApiResponse;
import com.interfaces.model.RegisterRequest;
import com.interfaces.model.UpdateUserRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ManageUserService {

    ApiResponse<?> registerUser(RegisterRequest request, String traceId);

    ApiResponse<?> updateUser(UpdateUserRequest request, String traceId);

    ApiResponse<?> getUserById(String id, String traceId);

    ApiResponse<?> isUserPrivate(String userId, String traceId);

    ApiResponse<?> isUserExists(String userId, String traceId);

    ApiResponse<?> updateImage(String id, MultipartFile file, String traceId);

    ApiResponse<?> getUserCount(String traceId);

    ApiResponse<?> deleteUser(String id, String traceId);

    ApiResponse<?> getUsersInRange(int fromIndex, int toIndex, String traceId);

    ApiResponse<?> getAllUsers(String traceId);
}
