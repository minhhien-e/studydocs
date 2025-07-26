package studydocs.notificationservice.domain.entities;


import studydocs.notificationservice.domain.valueobject.date.past.CreateDate;
import studydocs.notificationservice.domain.valueobject.notification.NotificationTypeValue;
import studydocs.notificationservice.domain.valueobject.template.TemplateData;
import studydocs.notificationservice.shared.exception.concrete.notification.MissingIdInNotificationException;
import studydocs.notificationservice.shared.exception.concrete.notification.MissingSenderIdInNotificationException;
import studydocs.notificationservice.shared.exception.concrete.notification.MissingTemplateIdInNotificationException;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.template.data.MissingTemplateDataFieldException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Notification {
    private final UUID id;
    private final UUID templateId;
    private final UUID senderId;
    private NotificationTypeValue type;
    private TemplateData templateData;
    private CreateDate createAt;

    public Notification(UUID id, UUID senderId, UUID templateId, String type, Map<String, Object> templateData, LocalDateTime createAt) {
        validateForLoad(id, templateId, senderId, templateData);
        this.id = id;
        this.senderId = senderId;
        this.templateId = templateId;
        this.type = new NotificationTypeValue(type);
        this.templateData = new TemplateData(templateData);
        this.createAt = new CreateDate("tạo", "thông báo", createAt);
    }

    public Notification(UUID templateId, UUID senderId, String type, Map<String, Object> templateData) {
        validateForCreate(templateId, senderId, templateData);
        this.id = UUID.randomUUID();
        this.senderId = senderId;
        this.type = new NotificationTypeValue(type);
        this.templateData = new TemplateData(templateData);
        this.templateId = templateId;
        this.createAt = new CreateDate("tạo", "thông báo", LocalDateTime.now());
    }

    public UUID getSenderId() {
        return senderId;
    }

    public UUID getId() {
        return id;
    }

    public NotificationTypeValue getType() {
        return type;
    }

    public UUID getTemplateId() {
        return templateId;
    }


    public TemplateData getTemplateData() {
        return templateData;
    }

    public CreateDate getCreateAt() {
        return createAt;
    }

    private void validateForLoad(UUID id, UUID templateId, UUID senderId, Map<String, Object> templateData) {
        if (id == null)
            throw new MissingIdInNotificationException();
        validateForCreate(templateId, senderId, templateData);
    }

    private void validateForCreate(UUID templateId, UUID senderId, Map<String, Object> templateData) {
        if (templateId == null)
            throw new MissingTemplateIdInNotificationException();
        if (templateData == null ||
                templateData.isEmpty() ||
                templateData.values().stream().anyMatch(Objects::isNull))
            throw new MissingTemplateDataFieldException();
        if (senderId == null)
            throw new MissingSenderIdInNotificationException();
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
}
