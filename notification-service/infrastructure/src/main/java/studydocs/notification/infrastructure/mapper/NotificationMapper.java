package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.NotificationProjection;
import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.infrastructure.persistence.entity.NotificationEntity;

public final class NotificationMapper {
    public static NotificationEntity toEntity(Notification notification) {
        return NotificationEntity.builder()
                .id(notification.getId())
                .templateId(notification.getTemplateId())
                .senderId(notification.getSenderId())
                .templateData(notification.getTemplateData().value())
                .category(notification.getCategory().value())
                .channel(notification.getChannel().value())
                .createdAt(notification.getCreatedAt().value())
                .build();
    }

    public static Notification toDomain(NotificationEntity entity) {
        return Notification.reconstruct(entity.getId(), entity.getTemplateId(), entity.getSenderId(), entity.getCategory(), entity.getChannel(), entity.getTemplateData(), entity.getCreatedAt(), entity.getNotificationRecipients().stream().map(NotificationRecipientMapper::toDomain).toList());
    }

    public static NotificationProjection toProjection(NotificationEntity entity) {
        return new NotificationProjection(
                entity.getId(),
                entity.getTemplateId(),
                entity.getSenderId(),
                entity.getChannel(),
                entity.getCategory(),
                entity.getTemplateData(),
                entity.getCreatedAt(),
                entity.getNotificationRecipients().stream().map(NotificationRecipientMapper::toProjection).toList(),
                NotificationTemplateMapper.toProjection(entity.getNotificationTemplate())
        );
    }
}
