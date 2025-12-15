package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.MarkAllAsReadCommand;
import studydocs.notification.application.port.in.usecase.notification.MarkAllAsReadUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class MarkAllAsReadCommandHandler 
    extends AbstractHandler<MarkAllAsReadCommand, Void, MarkAllAsReadUseCasePort> {
    
    protected MarkAllAsReadCommandHandler(MarkAllAsReadUseCasePort useCase) {
        super(useCase, MarkAllAsReadCommand.class);
    }
}
