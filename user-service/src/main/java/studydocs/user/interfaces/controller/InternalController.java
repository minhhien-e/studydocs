package studydocs.user.interfaces.controller;

import studydocs.user.application.ManageUserService;
import studydocs.user.interfaces.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/internal/users")
@Slf4j
@RequiredArgsConstructor
public class InternalController {
    private final ManageUserService manageUserService;

    @GetMapping("/{id}")
    public ApiResponse<?> getUserByID(
            @PathVariable UUID id,
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
    /** Lấy danh sách tất cả user */
    @GetMapping("/all")
    public ApiResponse<?> getAllUsers(
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        log.info("[traceId: {}] Nhận request lấy danh sách tất cả người dùng", traceId);
        return manageUserService.getAllUsers(traceId);
    }
}
