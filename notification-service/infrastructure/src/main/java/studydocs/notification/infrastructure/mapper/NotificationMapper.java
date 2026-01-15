package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.NotificationProjection;
import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.infrastructure.persistence.entity.NotificationEntity;

public final class NotificationMapper {
    public static NotificationProjection toProjection(NotificationEntity entity) {
        return NotificationProjection.builder()
                .id(entity.getId())
                .templateId(entity.getTemplateId())
                .senderId(entity.getSenderId())
                .channel(entity.getChannel())
                .type(entity.getType())
                .snapshotSubject(entity.getSnapshotSubject())
                .snapshotBody(entity.getSnapshotBody())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static Notification toDomain(NotificationEntity entity) {
        return Notification.reconstruct(
                entity.getId(),
                entity.getVersion(),
                entity.getTemplateId(),
                entity.getSenderId(),
                entity.getType(),
                entity.getChannel(),
                entity.getSnapshotSubject(),
                entity.getSnapshotBody(),
                entity.getCreatedAt()
        );
    }

    public static void updateEntity(NotificationEntity entity, Notification domain) {
        if (entity.getId() == null) {
            entity.setId(domain.getId());
        }
        entity.setTemplateId(domain.getTemplateId());
        entity.setSenderId(domain.getSenderId());
        entity.setSnapshotSubject(domain.getSnapshotSubject().value());
        entity.setSnapshotBody(domain.getSnapshotBody().value());
        entity.setType(domain.getType().value());
        entity.setChannel(domain.getChannel().value());
        entity.setCreatedAt(domain.getCreatedAt().value());
    }
}
