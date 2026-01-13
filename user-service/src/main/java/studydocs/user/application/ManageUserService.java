package studydocs.user.application;

import studydocs.user.interfaces.model.ApiResponse;
import studydocs.user.interfaces.model.RegisterRequest;
import studydocs.user.interfaces.model.UpdateUserRequest;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface ManageUserService {

    ApiResponse<?> registerUser(RegisterRequest request, String traceId);

    ApiResponse<?> updateUser(UpdateUserRequest request, String traceId);

    ApiResponse<?> getUserById(UUID id, String traceId);

    ApiResponse<?> isUserPrivate(UUID userId, String traceId);

    ApiResponse<?> isUserExists(UUID userId, String traceId);

    ApiResponse<?> updateImage(UUID id, MultipartFile file, String traceId);

    ApiResponse<?> getUserCount(String traceId);

    ApiResponse<?> deleteUser(UUID id, String traceId);

    ApiResponse<?> getUsersInRange(int fromIndex, int toIndex, String traceId);

    ApiResponse<?> getAllUsers(String traceId);
}
