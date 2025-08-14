package studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.create;

public record AddTemplateRequest(
        String name,
        String channel,
        String subjectTemplate,
        String bodyTemplate,
        String description) {
}
