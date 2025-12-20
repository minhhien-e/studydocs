package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.vo.NotificationDeletionTime;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;

public final class NotificationRecipientMapper {
    public static NotificationRecipientEntity toEntity(NotificationRecipient domain) {
        return NotificationRecipientEntity.builder()
                .id(domain.getId())
                .notificationId(domain.getNotificationId())
                .recipientId(domain.getRecipientId())
                .renderedSubject(domain.getRenderedSubject())
                .renderedBody(domain.getRenderedBody())
                .isRead(domain.isRead())
                .receivedAt(domain.getReceptionTime().value())
                .deletedAt(domain.getDeletedAt().map(NotificationDeletionTime::value).orElse(null))
                .build();
    }

    public static NotificationRecipient toDomain(NotificationRecipientEntity entity) {
        return NotificationRecipient.reconstruct(
                entity.getId(),
                entity.getNotificationId(),
                entity.getRecipientId(),
                entity.getRenderedSubject(),
                entity.getRenderedBody(),
                entity.getIsRead(),
                entity.getReceivedAt(),
                entity.getDeletedAt()
        );
    }
    public static NotificationRecipientProjection toProjection(NotificationRecipientEntity entity) {
        return NotificationRecipientProjection.builder()
                .id(entity.getId())
                .recipientId(entity.getRecipientId())
                .renderedSubject(entity.getRenderedSubject())
                .renderedBody(entity.getRenderedBody())
                .isRead(entity.getIsRead())
                .receivedAt(entity.getReceivedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
