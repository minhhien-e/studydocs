package studydocs.notificationservice.infrastructure.mongo.mapper;

import studydocs.notificationservice.domain.entities.Notification;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationDocument;

public final class NotificationMapper {

    public static Notification toDomain(NotificationDocument document) {
        if (document == null) return null;
        return new Notification(document.getId(),
                document.getTemplateId(),
                document.getSenderId(),
                document.getType(),
                document.getTemplateData(),
                document.getCreatedAt());
    }

    public static NotificationDocument toDocument(Notification notification) {
        return NotificationDocument.builder()
                .id(notification.getId())
                .templateId(notification.getTemplateId())
                .senderId(notification.getSenderId())
                .templateData(notification.getTemplateData().data())
                .type(notification.getType().getValue())
                .createdAt(notification.getCreateAt().getValue())
                .build();
    }
}
