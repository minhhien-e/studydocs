package studydocs.notification.infrastructure.mapper;

import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.infrastructure.persistence.entity.NotificationEntity;

public final class NotificationMapper {
    public static NotificationEntity toEntity(Notification notification) {
        return NotificationEntity.builder()
                .id(notification.getId())
                .templateId(notification.getTemplateId())
                .senderId(notification.getSenderId())
                .snapshotSubject(notification.getSnapshotSubject().value())
                .snapshotBody(notification.getSnapshotBody().value())
                .category(notification.getCategory().value())
                .channel(notification.getChannel().value())
                .createdAt(notification.getCreatedAt().value())
                .build();
    }

    public static Notification toDomain(NotificationEntity entity) {
        return Notification.reconstruct(
                entity.getId(),
                entity.getTemplateId(),
                entity.getSenderId(),
                entity.getCategory(),
                entity.getChannel(),
                entity.getSnapshotSubject(),
                entity.getSnapshotBody(),
                entity.getCreatedAt()
        );
    }
}
