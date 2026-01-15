package studydocs.notification.domain.aggregate;

import io.github.domain.aggregate.AggregateRoot;
import studydocs.notification.domain.exception.template.TemplateDescriptionNullOrEmptyException;
import studydocs.notification.domain.exception.template.TemplateDescriptionTooLongException;
import studydocs.notification.domain.exception.template.TemplateDescriptionTooShortException;
import studydocs.notification.domain.vo.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


public class NotificationTemplate extends AggregateRoot {
    private TemplateName name;
    private TemplateChannel channel;
    private TemplateSubject subjectTemplate;
    private TemplateBody bodyTemplate;
    private String description;
    private TemplateCreationTime createdAt;
    private TemplateUpdateTime updatedAt;
    private TemplateType type;

    /// Constructor
    private NotificationTemplate(UUID id, long version) {
        super(id, version);
    }

    private NotificationTemplate() {
        super();
    }

    /// Business logic
    public void editBody(String body) {
        this.bodyTemplate = new TemplateBody(body);
        update();
    }

    public void editSubject(String subject) {
        this.subjectTemplate = new TemplateSubject(subject);
        update();

    }

    public void editChannel(String channel) {
        this.channel = new TemplateChannel(channel);
        update();

    }

    public void rename(String name) {
        this.name = new TemplateName(name);
        update();

    }

    public void editDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new TemplateDescriptionNullOrEmptyException();
        }
        if (description.length() < 10) {
            throw new TemplateDescriptionTooShortException();
        }
        if (description.length() > 500) {
            throw new TemplateDescriptionTooLongException();
        }
        this.description = description;
        update();
    }

    private void update() {
        this.updatedAt = new TemplateUpdateTime(LocalDateTime.now());
    }

    /// Factory method
    public static NotificationTemplate create(String name, String channel, String subject, String body, String description, String type) {
        NotificationTemplate template = new NotificationTemplate();
        template.name = new TemplateName(name);
        template.channel = new TemplateChannel(channel);
        template.subjectTemplate = new TemplateSubject(subject);
        template.bodyTemplate = new TemplateBody(body);
        template.description = Optional.ofNullable(description).map(d -> {
            template.editDescription(d);
            return template.description;
        }).orElse(null);
        template.createdAt = new TemplateCreationTime(LocalDateTime.now());
        template.updatedAt = new TemplateUpdateTime(LocalDateTime.now());
        template.type = new TemplateType(type);
        return template;
    }

    public static NotificationTemplate reconstruct(UUID id, long version, String name, String channel, String subject, String body, String description, LocalDateTime createdAt, LocalDateTime updatedAt, String type) {
        NotificationTemplate template = new NotificationTemplate(id,version);
        template.name = new TemplateName(name);
        template.channel = new TemplateChannel(channel);
        template.subjectTemplate = new TemplateSubject(subject);
        template.bodyTemplate = new TemplateBody(body);
        template.description = description;
        template.createdAt = new TemplateCreationTime(createdAt);
        template.updatedAt = new TemplateUpdateTime(updatedAt);
        template.type = new TemplateType(type);
        return template;
    }

    /// Getter
    public TemplateName getName() {
        return name;
    }

    public TemplateChannel getChannel() {
        return channel;
    }

    public TemplateSubject getSubjectTemplate() {
        return subjectTemplate;
    }

    public TemplateBody getBodyTemplate() {
        return bodyTemplate;
    }

    public String getDescription() {
        return description;
    }

    public TemplateCreationTime getCreatedAt() {
        return createdAt;
    }

    public TemplateUpdateTime getUpdatedAt() {
        return updatedAt;
    }

    public TemplateType getType() {
        return type;
    }
}
