package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.HardDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.HardDeleteNotificationUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class HardDeleteNotificationHandler
    extends AbstractHandler<HardDeleteNotificationCommand, Void, HardDeleteNotificationUseCasePort> {
    
    protected HardDeleteNotificationHandler(HardDeleteNotificationUseCasePort useCase) {
        super(useCase, HardDeleteNotificationCommand.class);
    }
}
