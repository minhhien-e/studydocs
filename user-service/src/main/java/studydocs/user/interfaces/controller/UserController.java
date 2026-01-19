package studydocs.user.interfaces.controller;

import studydocs.user.application.ManageUserService;
import studydocs.user.domain.repository.UserRepository;
import studydocs.user.infrastructure.JwtCurrentUserProvider;
import studydocs.user.interfaces.model.ApiResponse;
import studydocs.user.interfaces.model.RegisterRequest;
import studydocs.user.interfaces.model.UpdateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import studydocs.user.interfaces.security.JwtCurrentTokenProvider;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final ManageUserService manageUserService;
    private final JwtCurrentUserProvider jwtCurrentUserProvider;
    @PostMapping("/register")
    public ApiResponse<?> register(
            @Valid @RequestBody RegisterRequest request,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request đăng ký người dùng", traceId);
        return manageUserService.registerUser(request, traceId);
    }

    //    @PreAuthorize("hasAuthority('user.update')")
    @PatchMapping ("/update")
    public ApiResponse<?> update(
            @Valid @RequestBody UpdateUserRequest request,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request cập nhật người dùng", traceId);
        return manageUserService.updateUser(request, traceId);
    }

    //    @PreAuthorize("hasAuthority('user.read')")
    @GetMapping("/getUserByID")
    public ApiResponse<?> getUserByID(
            @RequestParam UUID id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request lấy thông tin user id={}", traceId, id);
        return manageUserService.getUserById(id, traceId);
    }

    @GetMapping("/isPrivate")
    public ApiResponse<?> checkUserPrivate(
            @RequestParam UUID id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request kiểm tra trạng thái private id={}", traceId, id);
        return manageUserService.isUserPrivate(id, traceId);
    }

    @GetMapping("/exists")
    public ApiResponse<?> checkUserExists(
            @RequestParam UUID id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request kiểm tra user tồn tại id={}", traceId, id);
        return manageUserService.isUserExists(id, traceId);
    }

    @PostMapping("/updateImage")
    public ApiResponse<?> updateImage(
            @RequestParam("file") MultipartFile file,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request cập nhật ảnh user id={}", traceId, jwtCurrentUserProvider.getCurrentUserId());
        return manageUserService.updateImage(jwtCurrentUserProvider.getCurrentUserId(), file, traceId);
    }
//    @PreAuthorize("hasAuthority('user.read.all')")

    /**
     * Lấy danh sách tất cả user
     */
    @GetMapping("/all")
    public ApiResponse<?> getAllUsers(
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request lấy danh sách tất cả người dùng", traceId);
        return manageUserService.getAllUsers(traceId);
    }
//    @PreAuthorize("hasAuthority('user.read.count')")

    /**
     * Lấy tổng số user
     */
    @GetMapping("/count")
    public ApiResponse<?> getUserCount(
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request lấy tổng số người dùng", traceId);
        return manageUserService.getUserCount(traceId);
    }

    /**
     * Xóa user theo ID
     */
//    @PreAuthorize("hasAuthority('user.delete')")
    @DeleteMapping("/delete")
    public ApiResponse<?> deleteUser(
            @RequestParam UUID id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request xóa user id={}", traceId, id);
        return manageUserService.deleteUser(id, traceId);
    }

    //    @PreAuthorize("hasAuthority('user.read.all')")
    @GetMapping("/range")
    public ApiResponse<?> getUsersInRange(
            @RequestParam int fromIndex,
            @RequestParam int toIndex,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request lấy danh sách user từ index {} đến {}",
                traceId, fromIndex, toIndex);

        return manageUserService.getUsersInRange(fromIndex, toIndex, traceId);
    }

}
