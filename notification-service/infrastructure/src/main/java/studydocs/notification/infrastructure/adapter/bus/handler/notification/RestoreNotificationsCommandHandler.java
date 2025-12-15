package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.RestoreNotificationsCommand;
import studydocs.notification.application.port.in.usecase.notification.RestoreNotificationsUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class RestoreNotificationsCommandHandler 
    extends AbstractHandler<RestoreNotificationsCommand, Void, RestoreNotificationsUseCasePort> {
    
    protected RestoreNotificationsCommandHandler(RestoreNotificationsUseCasePort useCase) {
        super(useCase, RestoreNotificationsCommand.class);
    }
}
