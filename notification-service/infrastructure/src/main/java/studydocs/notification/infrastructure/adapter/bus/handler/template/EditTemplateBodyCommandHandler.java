package studydocs.notification.infrastructure.adapter.bus.handler.template;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.EditTemplateBodyCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateBodyUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class EditTemplateBodyCommandHandler 
    extends AbstractHandler<EditTemplateBodyCommand, Void, EditTemplateBodyUseCasePort> {
    
    protected EditTemplateBodyCommandHandler(EditTemplateBodyUseCasePort useCase) {
        super(useCase, EditTemplateBodyCommand.class);
    }
}
