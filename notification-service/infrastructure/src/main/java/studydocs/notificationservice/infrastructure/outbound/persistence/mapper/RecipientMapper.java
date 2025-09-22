package studydocs.notificationservice.infrastructure.outbound.persistence.mapper;

import studydocs.notificationservice.application.dto.output.UserNotificationDto;
import studydocs.notificationservice.domain.model.entity.Recipient;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.NotificationDocument;
import studydocs.notificationservice.infrastructure.outbound.persistence.entity.RecipientDocument;

public final class RecipientMapper {
    public static Recipient toDomain(RecipientDocument document) {
        return new Recipient(document.getId(), document.getRecipientId(), document.getNotificationId(), document.isRead(), document.getDeletedAt());
    }

    public static RecipientDocument toDocument(Recipient recipient) {
        return RecipientDocument.builder().id(recipient.getId()).recipientId(recipient.getRecipientId()).notificationId(recipient.getNotificationId()).isRead(recipient.isRead()).build();
    }

    public static UserNotificationDto toDto(NotificationDocument notification, RecipientDocument recipient) {
        return new UserNotificationDto(recipient.getId(), notification.getSenderId(), recipient.getRecipientId(), notification.getTemplateId(), notification.getTemplateData(), recipient.isRead(), recipient.getDeletedAt(), notification.getCreatedAt());
    }
}
