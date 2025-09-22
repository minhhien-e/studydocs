package studydocs.notificationservice.domain.factory.abstracts;

import studydocs.notificationservice.domain.model.entity.Notification;

import java.util.Map;
import java.util.UUID;

public interface NotificationFactory {
    Notification create(UUID templateId, UUID senderId, String category, Map<String, Object> templateData);
}
