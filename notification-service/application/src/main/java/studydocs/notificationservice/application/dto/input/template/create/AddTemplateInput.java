package studydocs.notificationservice.application.dto.input.template.create;

public record AddTemplateInput(String name,
                               String channel,
                               String subjectTemplate,
                               String bodyTemplate,
                               String description) {
}
