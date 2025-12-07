package studydocs.notification.domain.entity;

import io.github.domain.base.DirtyTracking;
import io.github.domain.entity.base.DomainEntity;
import studydocs.notification.domain.exception.template.InvalidDescriptionTemplateException;
import studydocs.notification.domain.vo.*;

import java.time.LocalDateTime;
import java.util.UUID;


public class NotificationTemplate extends DomainEntity {
    private TemplateName name;
    private TemplateChannel channel;
    private TemplateSubject subjectTemplate;
    private TemplateBody bodyTemplate;
    private String description;
    private TemplateCreationTime createdAt;
    private TemplateUpdateTime updatedAt;

    /// Constructor
    public NotificationTemplate(UUID id) {
        super(id, new DirtyTracking());
    }

    public NotificationTemplate() {
        super(new DirtyTracking());
    }

    /// Business logic
    public void editBody(String body) {
        this.bodyTemplate = new TemplateBody(body);
        markChanged("bodyTemplate");
        update();
    }

    public void editSubject(String subject) {
        this.subjectTemplate = new TemplateSubject(subject);
        markChanged("subjectTemplate");
        update();

    }

    public void editChannel(String channel) {
        this.channel = new TemplateChannel(channel);
        markChanged("channel");
        update();

    }

    public void rename(String name) {
        this.name = new TemplateName(name);
        markChanged("name");
        update();

    }

    public void editDescription(String description) {
        if(description == null) {
            throw new InvalidDescriptionTemplateException();
        }
        this.description = description;
        markChanged("description");
        update();
    }

    private void update() {
        this.updatedAt = new TemplateUpdateTime(LocalDateTime.now());
        markChanged("updatedAt");
    }

    /// Factory method
    public static NotificationTemplate create(String name, String channel, String subject, String body, String description) {
        NotificationTemplate template = new NotificationTemplate();
        template.rename(name);
        template.editChannel(channel);
        template.editSubject(subject);
        template.editBody(body);
        template.description = description;
        template.createdAt = new TemplateCreationTime(LocalDateTime.now());
        return template;
    }

    public static NotificationTemplate reconstruct(UUID id, String name, String channel, String subject, String body, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        NotificationTemplate template = new NotificationTemplate(id);
        template.name = new TemplateName(name);
        template.channel = new TemplateChannel(channel);
        template.subjectTemplate = new TemplateSubject(subject);
        template.bodyTemplate = new TemplateBody(body);
        template.description = description;
        template.createdAt = new TemplateCreationTime(createdAt);
        template.updatedAt = new TemplateUpdateTime(updatedAt);
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
}

