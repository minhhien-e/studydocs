package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.ReceiveNotificationUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class ReceiveNotificationCommandHandler 
    extends AbstractHandler<ReceiveNotificationCommand, Void, ReceiveNotificationUseCasePort> {
    
    protected ReceiveNotificationCommandHandler(ReceiveNotificationUseCasePort useCase) {
        super(useCase, ReceiveNotificationCommand.class);
    }
}
