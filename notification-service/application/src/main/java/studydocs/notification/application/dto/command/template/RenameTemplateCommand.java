package studydocs.notification.application.dto.command.template;


import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record RenameTemplateCommand(UUID templateId, String newName)  implements Request<Void> {
}
