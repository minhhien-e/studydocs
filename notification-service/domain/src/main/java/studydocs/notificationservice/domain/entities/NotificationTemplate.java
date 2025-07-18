package studydocs.notificationservice.domain.entities;


import studydocs.notificationservice.shared.enums.NotificationChannel;
import studydocs.notificationservice.shared.exception.concrete.notification.validation.InvalidNotificationChanelException;
import studydocs.notificationservice.shared.exception.concrete.template.business.InvalidUpdateDateInTemplateException;
import studydocs.notificationservice.shared.exception.concrete.template.business.TemplateCreatedAtInFutureException;
import studydocs.notificationservice.shared.exception.concrete.template.business.TemplateUpdateAtInFutureException;
import studydocs.notificationservice.shared.exception.concrete.template.validation.*;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class NotificationTemplate {
    private UUID id;
    private String name;
    private NotificationChannel channel;
    private String subjectTemplate;
    private String bodyTemplate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NotificationTemplate(String name, String channel,
                                String subjectTemplate, String bodyTemplate, String description) {
        validateForCreate(name, channel, subjectTemplate, bodyTemplate);
        this.id = UUID.randomUUID();
        this.name = name;
        this.channel = NotificationChannel.valueOf(channel);
        this.subjectTemplate = subjectTemplate;
        this.bodyTemplate = bodyTemplate;
        this.description = description;
    }

    public NotificationTemplate(UUID id, String name, String channel,
                                String subjectTemplate, String bodyTemplate, String description,
                                LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateForLoad(id, name, channel, subjectTemplate, bodyTemplate, createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.channel = NotificationChannel.valueOf(channel);
        this.subjectTemplate = subjectTemplate;
        this.bodyTemplate = bodyTemplate;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getSubjectTemplate() {
        return subjectTemplate;
    }

    public String getBodyTemplate() {
        return bodyTemplate;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private void validateForLoad(UUID id, String name, String channel,
                                 String subjectTemplate, String bodyTemplate,
                                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (id == null)
            throw new MissingIdInTemplateException();
        if (createdAt == null)
            throw new MissingCreateAtInTemplateException();
        if (updatedAt == null)
            throw new MissingUpdateAtInTemplateException();
        if (LocalDateUtils.isFutureDate(createdAt))
            throw new TemplateCreatedAtInFutureException(createdAt);
        if (LocalDateUtils.isFutureDate(updatedAt))
            throw new TemplateUpdateAtInFutureException(updatedAt);
        if (LocalDateUtils.isAfter(createdAt, updatedAt))
            throw new InvalidUpdateDateInTemplateException(createdAt, updatedAt);
        validateForCreate(name, channel, subjectTemplate, bodyTemplate);
    }

    private void validateForCreate(String name, String channel,
                                   String subjectTemplate, String bodyTemplate) {
        if (name == null || name.isBlank())
            throw new MissingNameInTemplateException();
        if (channel == null || channel.isBlank())
            throw new MissingChanelInTemplateException();
        try {
            NotificationChannel.valueOf(channel);
        } catch (IllegalArgumentException ex) {
            throw new InvalidNotificationChanelException(channel);
        }
        if (subjectTemplate == null || subjectTemplate.isBlank())
            throw new MissingSubjectInTemplateException();
        if (bodyTemplate == null || bodyTemplate.isBlank())
            throw new MissingBodyInTemplateException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTemplate that = (NotificationTemplate) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
