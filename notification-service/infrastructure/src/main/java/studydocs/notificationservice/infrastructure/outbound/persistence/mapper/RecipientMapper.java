package studydocs.notificationservice.infrastructure.outbound.persistence.mapper;

import studydocs.notificationservice.domain.model.entity.NotificationRecipient;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationRecipientDocument;

public final class RecipientMapper {
    public static NotificationRecipient toDomain(NotificationRecipientDocument document) {
        return new NotificationRecipient(document.getId(),
                document.getRecipientId(),
                document.getNotificationId(),
                document.isRead(),
                document.getDeletedAt(),
                NotificationMapper.toDomain(document.getNotification())
        );
    }

    public static NotificationRecipientDocument toDocument(NotificationRecipient recipient) {
        return NotificationRecipientDocument.builder()
                .id(recipient.getId())
                .recipientId(recipient.getRecipientId())
                .notificationId(recipient.getNotificationId())
                .isRead(recipient.isRead())
                .build();
    }
}
