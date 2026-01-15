package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.vo.NotificationDeletionTime;
import studydocs.notification.infrastructure.persistence.entity.NotificationRecipientEntity;

public final class NotificationRecipientMapper {

    public static NotificationRecipient toDomain(NotificationRecipientEntity entity) {
        return NotificationRecipient.reconstruct(
                entity.getId(),
                entity.getVersion(),
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

    public static void updateEntity(NotificationRecipientEntity entity, NotificationRecipient domain) {
        if (entity.getId() == null) {
            entity.setId(domain.getId());
        }
        entity.setNotificationId(domain.getNotificationId());
        entity.setRecipientId(domain.getRecipientId());
        entity.setRenderedSubject(domain.getRenderedSubject());
        entity.setRenderedBody(domain.getRenderedBody());
        entity.setIsRead(domain.isRead());
        entity.setReceivedAt(domain.getReceptionTime().value());
        entity.setDeletedAt(domain.getDeletedAt().map(NotificationDeletionTime::value).orElse(null));
    }
}
