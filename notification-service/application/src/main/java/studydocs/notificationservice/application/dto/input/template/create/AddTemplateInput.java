package studydocs.notificationservice.application.dto.input.template.create;

import studydocs.notificationservice.domain.model.entity.NotificationTemplate;

public record AddTemplateInput(String name,
                               String channel,
                               String subjectTemplate,
                               String bodyTemplate,
                               String description) {
    public NotificationTemplate toDomain() {
        return new NotificationTemplate(name, channel, subjectTemplate, bodyTemplate, description);
    }
}
