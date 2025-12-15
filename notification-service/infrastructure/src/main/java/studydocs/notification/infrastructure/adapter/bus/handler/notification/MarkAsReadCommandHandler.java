package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.MarkAsReadCommand;
import studydocs.notification.application.port.in.usecase.notification.MarkAsReadUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class MarkAsReadCommandHandler 
    extends AbstractHandler<MarkAsReadCommand, Void, MarkAsReadUseCasePort> {
    
    protected MarkAsReadCommandHandler(MarkAsReadUseCasePort useCase) {
        super(useCase, MarkAsReadCommand.class);
    }
}
