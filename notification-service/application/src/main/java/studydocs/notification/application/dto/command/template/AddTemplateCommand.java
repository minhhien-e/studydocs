package studydocs.notification.application.dto.command.template;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

@Builder
public record AddTemplateCommand(String name,
                                 String channel,
                                 String subjectTemplate,
                                 String bodyTemplate,
                                 String description,
                                 String type) implements Request<Void> {
}
