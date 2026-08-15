package com.studydocs.modules.system.controller;

import com.studydocs.modules.system.dto.SystemDtos;
import com.studydocs.modules.system.service.NotificationService;
import com.studydocs.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ApiResponse<List<SystemDtos.NotificationDto>> getMyNotifications(Authentication authentication) {
        String userId = authentication.getName();
        return ApiResponse.success(notificationService.getUserNotifications(userId));
    }

    @GetMapping("/unread-count")
    public ApiResponse<Map<String, Long>> getUnreadCount(Authentication authentication) {
        String userId = authentication.getName();
        return ApiResponse.success(notificationService.getUnreadNotificationCount(userId));
    }

    @PutMapping("/{notificationId}/read")
    public ApiResponse<String> markAsRead(@PathVariable String notificationId, Authentication authentication) {
        String userId = authentication.getName();
        notificationService.markAsRead(notificationId, userId);
        return ApiResponse.success("Notification marked as read");
    }
}
