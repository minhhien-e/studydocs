package com.studydocs.modules.system.service;

import com.studydocs.modules.system.dto.SystemDtos;

import java.util.List;
import java.util.Map;

public interface NotificationService {
    List<SystemDtos.NotificationDto> getUserNotifications(String userId);
    Map<String, Long> getUnreadNotificationCount(String userId);
    void markAsRead(String notificationId, String userId);
    void createNotification(String userId, String title, String content, String type);
}
