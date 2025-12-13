package studydocs.notification.infrastructure.mapper;

import studydocs.notification.domain.aggregate.NotificationRecipient;
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
