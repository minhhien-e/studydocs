package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.CreateUserNotificationProfileCommand;
import studydocs.notification.application.port.in.usecase.userprofile.CreateUserNotificationProfileUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class CreateUserNotificationProfileCommandHandler 
    extends AbstractHandler<CreateUserNotificationProfileCommand, Void, CreateUserNotificationProfileUseCasePort> {
    
    protected CreateUserNotificationProfileCommandHandler(CreateUserNotificationProfileUseCasePort useCase) {
        super(useCase, CreateUserNotificationProfileCommand.class);
    }
}
