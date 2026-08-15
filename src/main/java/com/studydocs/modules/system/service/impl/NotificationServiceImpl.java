package com.studydocs.modules.system.service.impl;

import com.studydocs.modules.system.dto.SystemDtos;
import com.studydocs.modules.system.entity.NotificationEntity;
import com.studydocs.modules.system.repository.NotificationRepository;
import com.studydocs.modules.system.service.NotificationService;
import com.studydocs.shared.exception.AppException;
import com.studydocs.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SystemDtos.NotificationDto> getUserNotifications(String userId) {
        return notificationRepository.findByUserIdAndIsDeletedFalseOrderByReceivedAtDesc(userId).stream()
                .map(this::toNotificationDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getUnreadNotificationCount(String userId) {
        long count = notificationRepository.countByUserIdAndIsReadFalseAndIsDeletedFalse(userId);
        return Map.of("count", count);
    }

    @Override
    @Transactional
    public void markAsRead(String notificationId, String userId) {
        NotificationEntity notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, "Notification not found"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void createNotification(String userId, String title, String content, String type) {
        NotificationEntity entity = NotificationEntity.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .type(type)
                .isRead(false)
                .isDeleted(false)
                .receivedAt(LocalDateTime.now())
                .build();
        notificationRepository.save(entity);
    }

    private SystemDtos.NotificationDto toNotificationDto(NotificationEntity entity) {
        return SystemDtos.NotificationDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .type(entity.getType())
                .isRead(entity.getIsRead())
                .isDeleted(entity.getIsDeleted())
                .receivedAt(entity.getReceivedAt())
                .build();
    }
}
