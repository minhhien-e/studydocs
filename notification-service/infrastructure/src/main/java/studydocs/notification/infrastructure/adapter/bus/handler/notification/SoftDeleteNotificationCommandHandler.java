package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.SoftDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.SoftDeleteNotificationUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class SoftDeleteNotificationCommandHandler 
    extends AbstractHandler<SoftDeleteNotificationCommand, Void, SoftDeleteNotificationUseCasePort> {
    
    protected SoftDeleteNotificationCommandHandler(SoftDeleteNotificationUseCasePort useCase) {
        super(useCase, SoftDeleteNotificationCommand.class);
    }
}
