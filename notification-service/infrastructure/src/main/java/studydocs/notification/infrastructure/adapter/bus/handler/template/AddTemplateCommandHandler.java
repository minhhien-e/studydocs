package studydocs.notification.infrastructure.adapter.bus.handler.template;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.AddTemplateCommand;
import studydocs.notification.application.port.in.usecase.template.AddTemplateUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class AddTemplateCommandHandler 
    extends AbstractHandler<AddTemplateCommand, Void, AddTemplateUseCasePort> {
    
    protected AddTemplateCommandHandler(AddTemplateUseCasePort useCase) {
        super(useCase, AddTemplateCommand.class);
    }
}
