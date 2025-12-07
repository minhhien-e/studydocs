package studydocs.notification.application.dto.command.template;


import studydocs.notification.application.dto.base.Request;

public record AddTemplateCommand(String name,
                                 String channel,
                                 String subjectTemplate,
                                 String bodyTemplate,
                                 String description) implements Request<Void> {
}
