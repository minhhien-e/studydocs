package studydocs.notificationservice.domain.entity;


import studydocs.notificationservice.domain.valueobject.Name;
import studydocs.notificationservice.domain.valueobject.date.past.CreateDate;
import studydocs.notificationservice.domain.valueobject.date.past.UpdateDate;
import studydocs.notificationservice.domain.valueobject.notification.NotificationChannelType;
import studydocs.notificationservice.domain.valueobject.template.BodyTemplate;
import studydocs.notificationservice.domain.valueobject.template.SubjectTemplate;
import studydocs.notificationservice.shared.exception.concrete.template.MissingDescriptionInTemplateException;
import studydocs.notificationservice.shared.exception.concrete.template.MissingIdInTemplateException;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.name.NameAlreadyExistsException;
import studydocs.notificationservice.shared.utils.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class NotificationTemplate {
    private final UUID id;
    private Name name;
    private NotificationChannelType channel;
    private SubjectTemplate subjectTemplate;
    private BodyTemplate bodyTemplate;
    private String description;
    private CreateDate createdAt;
    private UpdateDate updatedAt;

    public NotificationTemplate(String name, String channel,
                                String subjectTemplate, String bodyTemplate, String description) {
        this.id = UUID.randomUUID();
        this.name = new Name(name, "mẫu thông báo");
        this.channel = new NotificationChannelType(channel, "mẫu thông báo");
        setSubjectTemplate(subjectTemplate);
        setBodyTemplate(bodyTemplate);
        this.description = description;
        this.createdAt = new CreateDate("tạo", "mẫu thông báo", LocalDateTime.now());
    }

    public NotificationTemplate(UUID id, String name, String channel,
                                String subjectTemplate, String bodyTemplate, String description,
                                LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (id == null)
            throw new MissingIdInTemplateException();
        this.id = id;
        setName(name);
        this.channel = new NotificationChannelType(channel, "mẫu thông báo");
        setSubjectTemplate(subjectTemplate);
        setBodyTemplate(bodyTemplate);
        this.description = description;
        this.createdAt = new CreateDate("tạo", "mẫu thông báo", createdAt);
        this.updatedAt = new UpdateDate("thay đổi", "mẫu thông báo", updatedAt, createdAt);
    }

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    private void setName(String name) {
        this.name = new Name(name, "mẫu thông báo");
    }

    public SubjectTemplate getSubjectTemplate() {
        return subjectTemplate;
    }

    private void setSubjectTemplate(String subjectTemplate) {
        this.subjectTemplate = new SubjectTemplate(subjectTemplate);
    }

    public BodyTemplate getBodyTemplate() {
        return bodyTemplate;
    }

    private void setBodyTemplate(String bodyTemplate) {
        this.bodyTemplate = new BodyTemplate(bodyTemplate);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public NotificationChannelType getChannel() {
        return channel;
    }

    public CreateDate getCreatedAt() {
        return createdAt;
    }

    public UpdateDate getUpdatedAt() {
        return updatedAt;
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

    public void updateName(String newName) {
        if (newName.equalsIgnoreCase(name.getValue()))
            throw new NameAlreadyExistsException("mẫu thông báo");
        setName(newName);
    }

    public void updateSubject(String newSubject) {
        setSubjectTemplate(newSubject);
    }

    public void updateBody(String newBody) {
        setBodyTemplate(newBody);
    }

    public void updateDescription(String newDescription) {
        if (StringUtils.isNullOrBlank(newDescription))
            throw new MissingDescriptionInTemplateException();
        else this.description = newDescription;
    }
}
