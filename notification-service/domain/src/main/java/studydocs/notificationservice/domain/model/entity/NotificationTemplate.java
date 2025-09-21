package studydocs.notificationservice.domain.model.entity;


import studydocs.notificationservice.domain.exceptions.vo.name.NameAlreadyExistsException;
import studydocs.notificationservice.domain.model.valueobject.channel.TemplateChannel;
import studydocs.notificationservice.domain.model.valueobject.date.past.TemplateCreationTime;
import studydocs.notificationservice.domain.model.valueobject.date.past.TemplateUpdatedTime;
import studydocs.notificationservice.domain.model.valueobject.name.Name;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.model.valueobject.template.BodyTemplate;
import studydocs.notificationservice.domain.model.valueobject.template.SubjectTemplate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class NotificationTemplate {
    private final UUID id;
    private Name name;
    private TemplateChannel channel;
    private SubjectTemplate subjectTemplate;
    private BodyTemplate bodyTemplate;
    private String description;
    private final TemplateCreationTime creationTime;
    private TemplateUpdatedTime updatedTime;

    public NotificationTemplate(Name name, TemplateChannel channel, SubjectTemplate subjectTemplate, BodyTemplate bodyTemplate, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.channel = channel;
        this.description = description;
        this.subjectTemplate = subjectTemplate;
        this.bodyTemplate = bodyTemplate;
        this.creationTime = new TemplateCreationTime(LocalDateTime.now());
    }

    public NotificationTemplate(UUID id, String name, String channel, String subjectTemplate, String bodyTemplate, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = new TemplateName(name);
        this.channel = new TemplateChannel(channel);
        this.subjectTemplate = new SubjectTemplate(subjectTemplate);
        this.bodyTemplate = new BodyTemplate(bodyTemplate);
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

    public void updateChannel(TemplateChannel newChannel) {
        this.channel = newChannel;
        this.updatedTime = new TemplateUpdatedTime(LocalDateTime.now(), creationTime);
    }

    public void updateSubject(SubjectTemplate newSubject) {
        this.subjectTemplate = newSubject;
        this.updatedTime = new TemplateUpdatedTime(LocalDateTime.now(), creationTime);
    }

    public void updateBody(BodyTemplate newBody) {
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
        NotificationTemplate that = (NotificationTemplate) o;
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

    public TemplateChannel getChannel() {
        return channel;
    }

    public SubjectTemplate getSubjectTemplate() {
        return subjectTemplate;
    }

    public BodyTemplate getBodyTemplate() {
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
