package studydocs.notificationservice.infrastructure.mongo.mapper;

import studydocs.notificationservice.domain.entities.NotificationRecipient;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationRecipientDocument;

public final class RecipientMapper {
    public static NotificationRecipient toDomain(NotificationRecipientDocument document) {
        return new NotificationRecipient(document.getId(),
                document.getRecipientId(),
                document.getNotificationId(),
                document.isRead(),
                document.isDeleted()
        );
    }

    public static NotificationRecipientDocument toDocument(NotificationRecipient recipient) {
        return NotificationRecipientDocument.builder()
                .id(recipient.getId())
                .recipientId(recipient.getRecipientId())
                .notificationId(recipient.getNotificationId())
                .isRead(recipient.isRead())
                .isDeleted(recipient.isDeleted())
                .build();
    }
}
