package studydocs.notificationservice.domain.model.entity;


import studydocs.notificationservice.domain.model.valueobject.TemplateData;
import studydocs.notificationservice.domain.model.valueobject.category.NotificationCategory;
import studydocs.notificationservice.domain.model.valueobject.date.past.NotificationCreationTime;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Notification {
    private final UUID id;
    private final UUID templateId;
    private final UUID senderId;
    private NotificationCategory notificationCategory;
    private TemplateData templateData;
    private final NotificationCreationTime creationTime;

    public Notification(UUID id, UUID senderId, UUID templateId, String type, Map<String, Object> templateData, LocalDateTime createAt) {
        this.id = id;
        this.senderId = senderId;
        this.templateId = templateId;
        this.notificationCategory = new NotificationCategory(type);
        this.templateData = new TemplateData(templateData);
        this.creationTime = new NotificationCreationTime(createAt);
    }

    public Notification(UUID templateId, UUID senderId, NotificationCategory category, TemplateData templateData) {
        this.id = UUID.randomUUID();
        this.senderId = senderId;
        this.notificationCategory = category;
        this.templateData = templateData;
        this.templateId = templateId;
        this.creationTime = new NotificationCreationTime(LocalDateTime.now());
    }

    public void updateCategory(NotificationCategory newCategory) {
        this.notificationCategory = newCategory;
    }

    public void updateTemplateData(TemplateData newTemplateData) {
        this.templateData = newTemplateData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public UUID getSenderId() {
        return senderId;
    }

    public UUID getId() {
        return id;
    }

    public NotificationCategory getNotificationCategory() {
        return notificationCategory;
    }

    public UUID getTemplateId() {
        return templateId;
    }

    public TemplateData getTemplateData() {
        return templateData;
    }

    public NotificationCreationTime getCreationTime() {
        return creationTime;
    }

}
