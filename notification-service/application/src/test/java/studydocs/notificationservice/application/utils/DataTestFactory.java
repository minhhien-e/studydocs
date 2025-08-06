package studydocs.notificationservice.application.utils;

import studydocs.notificationservice.domain.entities.Notification;
import studydocs.notificationservice.domain.entities.NotificationRecipient;
import studydocs.notificationservice.domain.entities.NotificationTemplate;
import studydocs.notificationservice.shared.enums.NotificationChannel;

import java.util.Map;
import java.util.UUID;

public class DataTestFactory {
    // Domain Entities
    public static Notification createNotification(UUID templateId, UUID senderId, String type, Map<String, Object> templateData) {
        return new Notification(templateId, senderId, type, templateData);
    }

    public static NotificationRecipient createNotificationRecipient(UUID recipientId, UUID notificationId,Notification notification) {
        return new NotificationRecipient(UUID.randomUUID(),recipientId, notificationId,false,false,notification);
    }

    public static NotificationTemplate createNotificationTemplate(String name, String channel, String subjectTemplate, String bodyTemplate, String description) {
        return new NotificationTemplate(name, channel, subjectTemplate, bodyTemplate, description);
    }
    public static NotificationTemplate createNotificationTemplate() {
        return new NotificationTemplate("name", NotificationChannel.EMAIL.name(), "subjectTemplate", "bodyTemplate", "description");
    }
}