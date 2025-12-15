package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.AddNotificationUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class AddNotificationCommandHandler extends AbstractHandler<AddNotificationCommand,Void,AddNotificationUseCasePort> {
    protected AddNotificationCommandHandler(AddNotificationUseCasePort useCase) {
        super(useCase, AddNotificationCommand.class);
    }
}
