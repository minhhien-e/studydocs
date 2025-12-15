package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.UpdateEmailCommand;
import studydocs.notification.application.port.in.usecase.userprofile.UpdateEmailUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class UpdateEmailCommandHandler 
    extends AbstractHandler<UpdateEmailCommand, Void, UpdateEmailUseCasePort> {
    
    protected UpdateEmailCommandHandler(UpdateEmailUseCasePort useCase) {
        super(useCase, UpdateEmailCommand.class);
    }
}
