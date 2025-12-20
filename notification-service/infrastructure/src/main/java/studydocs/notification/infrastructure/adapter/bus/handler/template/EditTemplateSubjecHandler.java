package studydocs.notification.infrastructure.adapter.bus.handler.template;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.EditTemplateSubjectCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateSubjectUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class EditTemplateSubjecHandler
    extends AbstractHandler<EditTemplateSubjectCommand, Void, EditTemplateSubjectUseCasePort> {
    
    protected EditTemplateSubjecHandler(EditTemplateSubjectUseCasePort useCase) {
        super(useCase, EditTemplateSubjectCommand.class);
    }
}
