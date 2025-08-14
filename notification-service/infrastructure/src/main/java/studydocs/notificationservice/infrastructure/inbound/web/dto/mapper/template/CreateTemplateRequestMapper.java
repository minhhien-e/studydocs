package studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.template;

import studydocs.notificationservice.application.dto.input.template.create.AddTemplateInput;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.template.create.AddTemplateRequest;

public class CreateTemplateRequestMapper {
    public static AddTemplateInput toInput(AddTemplateRequest request) {
        return new AddTemplateInput(
                request.name(),
                request.channel(),
                request.subjectTemplate(),
                request.bodyTemplate(),
                request.description()
        );
    }
}
