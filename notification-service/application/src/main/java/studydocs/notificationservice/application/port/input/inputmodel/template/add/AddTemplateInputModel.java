package studydocs.notificationservice.application.port.input.inputmodel.template.add;

import studydocs.notificationservice.domain.entities.NotificationTemplate;

public record AddTemplateInputModel(String name,
                                    String channel,
                                    String subjectTemplate,
                                    String bodyTemplate,
                                    String description) {
    public NotificationTemplate toDomain() {
        return new NotificationTemplate(name, channel, subjectTemplate, bodyTemplate, description);
    }
}
