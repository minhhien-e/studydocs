package studydocs.notification.infrastructure.adapter.bus.handler.template;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.EditTemplateDescriptionCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateDescriptionUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class EditTemplateDescriptionCommandHandler 
    extends AbstractHandler<EditTemplateDescriptionCommand, Void, EditTemplateDescriptionUseCasePort> {
    
    protected EditTemplateDescriptionCommandHandler(EditTemplateDescriptionUseCasePort useCase) {
        super(useCase, EditTemplateDescriptionCommand.class);
    }
}
