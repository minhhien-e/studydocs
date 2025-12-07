package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.domain.entity.NotificationRecipient;
import studydocs.notification.domain.vo.NotificationDeletionTime;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;

public final class NotificationRecipientMapper {
    public static NotificationRecipientEntity toEntity(NotificationRecipient domain) {
        return NotificationRecipientEntity.builder()
                .id(domain.getId())
                .notificationId(domain.getNotificationId())
                .recipientId(domain.getRecipientId())
                .isRead(domain.isRead())
                .deletedAt(domain.getDeletedAt().map(NotificationDeletionTime::value).orElse(null))
                .personalizedData(domain.getPersonalizedData().value())
                .receivedAt(domain.getReceptionTime().value())
                .build();
    }

    public static NotificationRecipient toDomain(NotificationRecipientEntity entity) {
        return NotificationRecipient.reconstruct(entity.getId(), entity.getRecipientId(), entity.getNotificationId(), entity.getPersonalizedData(), entity.getIsRead(),entity.getReceivedAt(), entity.getDeletedAt());
    }
    public static NotificationRecipientProjection toProjection(NotificationRecipientEntity entity) {
        return new NotificationRecipientProjection(
                entity.getId(),
                entity.getRecipientId(),
                entity.getNotificationId(),
                entity.getIsRead(),
                entity.getPersonalizedData(),
                entity.getReceivedAt(),
                entity.getDeletedAt()
        );
    }
}
