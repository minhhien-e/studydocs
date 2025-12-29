package studydocs.notification.infrastructure.adapter.bus.handler.template;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.RenameTemplateCommand;
import studydocs.notification.application.port.in.usecase.template.RenameTemplateUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class RenameTemplateHandler
    extends AbstractHandler<RenameTemplateCommand, Void> {
    
    protected RenameTemplateHandler(RenameTemplateUseCasePort useCase) {
        super(useCase, RenameTemplateCommand.class);
    }
}
