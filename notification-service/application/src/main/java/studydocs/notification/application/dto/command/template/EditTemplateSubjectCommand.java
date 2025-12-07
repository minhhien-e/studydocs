package studydocs.notification.application.dto.command.template;



import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record EditTemplateSubjectCommand(UUID templateId, String newSubject)  implements Request<Void> {
}
