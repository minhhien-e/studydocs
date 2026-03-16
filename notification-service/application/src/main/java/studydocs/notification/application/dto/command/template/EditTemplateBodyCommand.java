package studydocs.notification.application.dto.command.template;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

@Builder
public record EditTemplateBodyCommand(UUID templateId, String newBody) implements Request<Void> {
}
