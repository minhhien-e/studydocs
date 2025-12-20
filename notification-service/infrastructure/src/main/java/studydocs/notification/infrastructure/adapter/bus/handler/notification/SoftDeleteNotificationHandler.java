package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.SoftDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.SoftDeleteNotificationUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class SoftDeleteNotificationHandler
    extends AbstractHandler<SoftDeleteNotificationCommand, Void> {
    
    protected SoftDeleteNotificationHandler(SoftDeleteNotificationUseCasePort useCase) {
        super(useCase, SoftDeleteNotificationCommand.class);
    }
}
