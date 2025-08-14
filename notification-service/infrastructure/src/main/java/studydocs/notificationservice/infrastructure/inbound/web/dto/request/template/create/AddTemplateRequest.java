package studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.create;

import studydocs.notificationservice.application.dto.input.template.create.AddTemplateInput;

public record AddTemplateRequest(
        String name,
        String channel,
        String subjectTemplate,
        String bodyTemplate,
        String description) {
    public AddTemplateInput toInput() {
        return new AddTemplateInput(
                name,
                channel,
                subjectTemplate,
                bodyTemplate,
                description
        );
    }
}
