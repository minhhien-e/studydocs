package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;

public final class NotificationRecipientMapper {
    /**
     * Maps entity to flattened projection for query results.
     * Flattens notification fields to avoid nesting and improve query performance.
     */
    public static NotificationRecipientProjection toProjection(
            NotificationRecipientEntity entity,
            String senderName
    ) {
        var notification = entity.getNotification();
        
        return NotificationRecipientProjection.builder()
                // Recipient fields
                .id(entity.getId())
                .recipientId(entity.getRecipientId())
                .renderedSubject(entity.getRenderedSubject())
                .renderedBody(entity.getRenderedBody())
                .isRead(entity.getIsRead())
                .receivedAt(entity.getReceivedAt())
                .deletedAt(entity.getDeletedAt())
                // Flattened notification fields
                .notificationId(entity.getNotificationId())
                .senderId(notification != null ? notification.getSenderId() : null)
                .senderName(senderName)
                .category(notification != null ? notification.getCategory() : null)
                .notificationCreatedAt(notification != null ? notification.getCreatedAt() : null)
                .build();
    }

    public static NotificationRecipientEntity toEntity(NotificationRecipient domain) {
        return NotificationRecipientEntity.builder()
                .id(domain.getId())
                .notificationId(domain.getNotificationId())
                .recipientId(domain.getRecipientId())
                .renderedSubject(domain.getRenderedSubject())
                .renderedBody(domain.getRenderedBody())
                .isRead(domain.isRead())
                .receivedAt(domain.getReceptionTime().value())
                .deletedAt(domain.getDeletedAt().map(dt -> dt.value()).orElse(null))
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
}
