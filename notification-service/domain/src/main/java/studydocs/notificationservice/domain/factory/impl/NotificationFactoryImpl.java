package studydocs.notificationservice.domain.factory.impl;

import studydocs.notificationservice.domain.factory.abstracts.NotificationFactory;
import studydocs.notificationservice.domain.model.entity.Notification;
import studydocs.notificationservice.domain.model.valueobject.TemplateData;
import studydocs.notificationservice.domain.model.valueobject.category.NotificationCategory;

import java.util.Map;
import java.util.UUID;

public class NotificationFactoryImpl implements NotificationFactory {
    @Override
    public Notification create(UUID templateId, UUID senderId, String category, Map<String, Object> templateData) {
        return new Notification(templateId, senderId, new NotificationCategory(category), new TemplateData(templateData));
    }
}
