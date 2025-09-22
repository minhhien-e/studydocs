package studydocs.notificationservice.domain.model.entity;


import studydocs.notificationservice.domain.exceptions.vo.name.NameAlreadyExistsException;
import studydocs.notificationservice.domain.model.valueobject.channel.NotificationChannel;
import studydocs.notificationservice.domain.model.valueobject.date.past.TemplateCreationTime;
import studydocs.notificationservice.domain.model.valueobject.date.past.TemplateUpdatedTime;
import studydocs.notificationservice.domain.model.valueobject.name.Name;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.model.valueobject.template.TemplateBody;
import studydocs.notificationservice.domain.model.valueobject.template.TemplateSubject;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Template {
    private final UUID id;
    private Name name;
    private NotificationChannel channel;
    private TemplateSubject subjectTemplate;
    private TemplateBody bodyTemplate;
    private String description;
    private final TemplateCreationTime creationTime;
    private TemplateUpdatedTime updatedTime;

    public Template(Name name, NotificationChannel channel, TemplateSubject subjectTemplate, TemplateBody bodyTemplate, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.channel = channel;
        this.description = description;
        this.subjectTemplate = subjectTemplate;
        this.bodyTemplate = bodyTemplate;
        this.creationTime = new TemplateCreationTime(LocalDateTime.now());
    }

    public Template(UUID id, String name, String channel, String subjectTemplate, String bodyTemplate, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = new TemplateName(name);
        this.channel = new NotificationChannel(channel);
        this.subjectTemplate = new TemplateSubject(subjectTemplate);
        this.bodyTemplate = new TemplateBody(bodyTemplate);
        this.description = description;
        this.creationTime = new TemplateCreationTime(createdAt);
        this.updatedTime = new TemplateUpdatedTime(updatedAt, creationTime);
    }

    public void updateName(TemplateName newName) {
        if (!this.name.equals(newName)) {
            this.name = newName;
            this.updatedTime = new TemplateUpdatedTime(LocalDateTime.now(), creationTime);
        } else throw new NameAlreadyExistsException(newName.getValue());
    }

    public void updateChannel(NotificationChannel newChannel) {
        this.channel = newChannel;
        this.updatedTime = new TemplateUpdatedTime(LocalDateTime.now(), creationTime);
    }

    public void updateSubject(TemplateSubject newSubject) {
        this.subjectTemplate = newSubject;
        this.updatedTime = new TemplateUpdatedTime(LocalDateTime.now(), creationTime);
    }

    public void updateBody(TemplateBody newBody) {
        this.bodyTemplate = newBody;
        this.updatedTime = new TemplateUpdatedTime(LocalDateTime.now(), creationTime);
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
        this.updatedTime = new TemplateUpdatedTime(LocalDateTime.now(), creationTime);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Template that = (Template) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public NotificationChannel getChannel() {
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

    public TemplateCreationTime getCreationTime() {
        return creationTime;
    }

    public TemplateUpdatedTime getUpdatedTime() {
        return updatedTime;
    }
}
