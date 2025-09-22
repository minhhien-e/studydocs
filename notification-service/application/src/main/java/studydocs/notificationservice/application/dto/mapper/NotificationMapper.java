package studydocs.notificationservice.application.dto.mapper;

import studydocs.notificationservice.application.dto.output.NotificationDto;
import studydocs.notificationservice.application.dto.output.TemplateDto;
import studydocs.notificationservice.application.dto.output.UserNotificationDto;

public class NotificationMapper {
    public static NotificationDto toDto(UserNotificationDto userNotificationDto, TemplateDto templateDto, String content) {
        return NotificationDto.builder()
                .id(userNotificationDto.id())
                .recipientId(userNotificationDto.recipientId())
                .senderId(userNotificationDto.senderId())
                .subject(templateDto.subjectTemplate())
                .isRead(userNotificationDto.isRead())
                .creationTime(userNotificationDto.creationAt())
                .deletionTime(userNotificationDto.deletionTime())
                .content(content)
                .build();
    }
}
