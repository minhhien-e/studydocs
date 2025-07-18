package studydocs.notificationservice.infrastructure.mongo.mapper;

import studydocs.notificationservice.domain.entities.Notification;
import studydocs.notificationservice.infrastructure.mongo.document.NotificationDocument;

public final class NotificationMapper {

    public static Notification toDomain(NotificationDocument document) {
        return new Notification(document.getId(),
                document.getTemplateId(),
                document.getSenderId(),
                document.getChanel(),
                document.getTemplateData(),
                document.getCreatedAt());
    }

    public static NotificationDocument toDocument(Notification notification) {
        return NotificationDocument.builder()
                .templateId(notification.getTemplateId())
                .chanel(notification.getChanel().name())
                .senderId(notification.getSenderId())
                .templateData(notification.getTemplateData())
                .build();
    }
}
