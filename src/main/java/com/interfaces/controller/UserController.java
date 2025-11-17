package com.interfaces.controller;

import com.application.ManageUserService;
import com.interfaces.model.ApiResponse;
import com.interfaces.model.RegisterRequest;
import com.interfaces.model.UpdateUserRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        LOG.info("[traceId: {}] 📩 Nhận request đăng ký người dùng", traceId);
        return manageUserService.registerUser(request, traceId);
    }

    @PutMapping("/update")
    public ApiResponse<?> update(
            @Valid @RequestBody UpdateUserRequest request,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        LOG.info("[traceId: {}] ✏️ Nhận request cập nhật người dùng", traceId);
        return manageUserService.updateUser(request, traceId);
    }

    @GetMapping("/getUserByID")
    public ApiResponse<?> getUserByID(
            @RequestParam String id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        LOG.info("[traceId: {}] 🔍 Nhận request lấy thông tin user id={}", traceId, id);
        return manageUserService.getUserById(id, traceId);
    }

    @GetMapping("/isPrivate")
    public ApiResponse<?> checkUserPrivate(
            @RequestParam String id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        LOG.info("[traceId: {}] 🔒 Nhận request kiểm tra trạng thái private id={}", traceId, id);
        return manageUserService.isUserPrivate(id, traceId);
    }

    @GetMapping("/exists")
    public ApiResponse<?> checkUserExists(
            @RequestParam String id,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        LOG.info("[traceId: {}] 🧩 Nhận request kiểm tra user tồn tại id={}", traceId, id);
        return manageUserService.isUserExists(id, traceId);
    }

    @PostMapping("/updateImage")
    public ApiResponse<?> updateImage(
            @RequestParam String id,
            @RequestParam("file") MultipartFile file,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        LOG.info("[traceId: {}] 🖼️ Nhận request cập nhật ảnh user id={}", traceId, id);
        return manageUserService.updateImage(id, file, traceId);
    }
}
