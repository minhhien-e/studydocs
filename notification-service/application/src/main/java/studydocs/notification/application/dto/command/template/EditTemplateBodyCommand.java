package studydocs.notification.application.dto.command.template;



import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record EditTemplateBodyCommand(UUID templateId, String newBody) implements Request<Void> {
}
