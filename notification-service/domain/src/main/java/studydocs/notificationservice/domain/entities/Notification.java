package studydocs.notificationservice.domain.entities;


import studydocs.notificationservice.shared.enums.NotificationChannel;
import studydocs.notificationservice.shared.enums.NotificationType;
import studydocs.notificationservice.shared.exception.concrete.notification.business.NotificationCreatedAtInFutureException;
import studydocs.notificationservice.shared.exception.concrete.notification.validation.*;
import studydocs.notificationservice.shared.exception.concrete.notification.validation.InvalidNotificationChanelException;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;
import java.util.*;

public class Notification {
    private UUID id;
    private UUID templateId;
    private UUID senderId;
    private NotificationChannel chanel;
    private NotificationType type;
    private Map<String, Object> templateData;
    private LocalDateTime createAt;

    public Notification(UUID id, UUID senderId, UUID templateId, String chanel, String type, Map<String, Object> templateData, LocalDateTime createAt) {
        validateForLoad(id, templateId, senderId, chanel, type, templateData, createAt);
        this.id = id;
        this.senderId = senderId;
        this.templateId = templateId;
        this.chanel = NotificationChannel.valueOf(chanel);
        this.type = NotificationType.valueOf(type);
        this.templateData = templateData;
        this.createAt = createAt;
    }

    public Notification(UUID templateId, UUID senderId, String chanel, String type, Map<String, Object> templateData) {
        validateForCreate(templateId, senderId, chanel, type, templateData);
        this.id = UUID.randomUUID();
        this.chanel = NotificationChannel.valueOf(chanel);
        this.type = NotificationType.valueOf(type);
        this.templateData = templateData;
        this.templateId = templateId;
        this.createAt = LocalDateTime.now();
    }

    public UUID getSenderId() {
        return senderId;
    }

    public UUID getId() {
        return id;
    }

    public NotificationType getType() {
        return type;
    }

    public UUID getTemplateId() {
        return templateId;
    }

    public NotificationChannel getChanel() {
        return chanel;
    }

    public Map<String, Object> getTemplateData() {
        return templateData;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    private void validateForLoad(UUID id, UUID templateId, UUID senderId, String chanel, String type, Map<String, Object> templateData, LocalDateTime createAt) {
        if (id == null)
            throw new MissingIdInNotificationException();
        if (createAt == null)
            throw new MissingCreateAtInNotificationException();
        if (LocalDateUtils.isFutureDate(createAt))
            throw new NotificationCreatedAtInFutureException(createAt);
        validateForCreate(templateId, senderId, chanel, type, templateData);
    }

    private void validateForCreate(UUID templateId, UUID senderId, String chanel, String type, Map<String, Object> templateData) {
        if (templateId == null)
            throw new MissingTemplateIdInNotificationException();
        if (chanel == null || chanel.isBlank())
            throw new MissingChanelInNotificationException();
        try {
            NotificationChannel.valueOf(chanel);
        } catch (IllegalArgumentException ex) {
            throw new InvalidNotificationChanelException(chanel);
        }
        if (type == null || type.isBlank()) {
            throw new MissingTypeInNotificationException();
        }
        try {
            NotificationType.valueOf(type);
        } catch (IllegalArgumentException ex) {
            throw new InvalidNotificationTypeException(type);
        }
        if (templateData == null ||
                templateData.isEmpty() ||
                templateData.values().stream().anyMatch(Objects::isNull))
            throw new MissingTemplateDataInNotificationException();
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
