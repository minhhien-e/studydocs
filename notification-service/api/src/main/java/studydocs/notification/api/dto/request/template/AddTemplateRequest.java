package studydocs.notification.api.dto.request.template;

public record AddTemplateRequest(
        String name,
        String channel,
        String subjectTemplate,
        String bodyTemplate,
        String description,
        String type) {
}
