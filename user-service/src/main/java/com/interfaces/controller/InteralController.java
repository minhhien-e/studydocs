package com.interfaces.controller;

import com.application.ManageUserService;
import com.interfaces.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/internal/users")
public class InteralController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final ManageUserService manageUserService;
    public InteralController(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }
    @GetMapping("/{id}")
    public ApiResponse<?> getUserByID(
            @PathVariable UUID id,
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
    /** Lấy danh sách tất cả user */
    @GetMapping("/all")
    public ApiResponse<?> getAllUsers(
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {

        LOG.info("[traceId: {}] Nhận request lấy danh sách tất cả người dùng", traceId);
        return manageUserService.getAllUsers(traceId);
    }
}
