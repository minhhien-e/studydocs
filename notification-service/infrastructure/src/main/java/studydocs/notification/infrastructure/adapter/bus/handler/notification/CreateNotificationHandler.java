package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.CreateNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.CreateNotificationUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

import java.util.UUID;

@Component
public class CreateNotificationHandler extends AbstractHandler<CreateNotificationCommand, UUID> {
    protected CreateNotificationHandler(CreateNotificationUseCasePort useCase) {
        super(useCase, CreateNotificationCommand.class);
    }
}
