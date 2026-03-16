package studydocs.notification.application.dto.command.template;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

@Builder
public record EditTemplateSubjectCommand(UUID templateId, String newSubject)  implements Request<Void> {
}
