package studydocs.notification.application.dto.command.template;



import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record EditTemplateDescriptionCommand (UUID templateId, String newDescription) implements Request<Void> {
}
