package com.interfaces.controller;

import com.application.ManageUserService;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.RegisterRequest;
import com.interfaces.model.UpdateUserRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final ManageUserService manageUserService;

    public UserController(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }
   
    @PostMapping("/register")
    public ApiResponse<?> register(
            @Valid @RequestBody RegisterRequest request,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request đăng ký người dùng", traceId);
        return manageUserService.registerUser(request, traceId);
    }
    @PreAuthorize("hasAuthority('user.update')")
    @PutMapping("/update")
    public ApiResponse<?> update(
            @Valid @RequestBody UpdateUserRequest request,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request cập nhật người dùng", traceId);
        return manageUserService.updateUser(request, traceId);
    }
    @PreAuthorize("hasAuthority('user.read')")
    @GetMapping("/getUserByID")
    public ApiResponse<?> getUserByID(
            @RequestParam UUID id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request lấy thông tin user id={}", traceId, id);
        return manageUserService.getUserById(id, traceId);
    }

    @GetMapping("/isPrivate")
    public ApiResponse<?> checkUserPrivate(
            @RequestParam UUID id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request kiểm tra trạng thái private id={}", traceId, id);
        return manageUserService.isUserPrivate(id, traceId);
    }

    @GetMapping("/exists")
    public ApiResponse<?> checkUserExists(
            @RequestParam UUID id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request kiểm tra user tồn tại id={}", traceId, id);
        return manageUserService.isUserExists(id, traceId);
    }

    @PostMapping("/updateImage")
    public ApiResponse<?> updateImage(
            @RequestParam UUID id,
            @RequestParam("file") MultipartFile file,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request cập nhật ảnh user id={}", traceId, id);
        return manageUserService.updateImage(id, file, traceId);
    }
    @PreAuthorize("hasAuthority('user.read.all')")
    /** Lấy danh sách tất cả user */
    @GetMapping("/all")
    public ApiResponse<?> getAllUsers(
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request lấy danh sách tất cả người dùng", traceId);
        return manageUserService.getAllUsers(traceId);
    }
    @PreAuthorize("hasAuthority('user.read.count')")
    /** Lấy tổng số user */
    @GetMapping("/count")
    public ApiResponse<?> getUserCount(
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request lấy tổng số người dùng", traceId);
        return manageUserService.getUserCount(traceId);
    }
    /** Xóa user theo ID */
    @PreAuthorize("hasAuthority('user.delete')")
    @DeleteMapping("/delete")
    public ApiResponse<?> deleteUser(
            @RequestParam UUID id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request xóa user id={}", traceId, id);
        return manageUserService.deleteUser(id, traceId);
    }
    @PreAuthorize("hasAuthority('user.read.all')")
    @GetMapping("/range")
    public ApiResponse<?> getUsersInRange(
            @RequestParam int fromIndex,
            @RequestParam int toIndex,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request lấy danh sách user từ index {} đến {}",
                traceId, fromIndex, toIndex);

        return manageUserService.getUsersInRange(fromIndex, toIndex, traceId);
    }
}
