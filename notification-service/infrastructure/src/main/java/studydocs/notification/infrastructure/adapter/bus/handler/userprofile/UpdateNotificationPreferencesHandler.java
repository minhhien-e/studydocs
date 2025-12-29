package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.UpdateNotificationPreferencesCommand;
import studydocs.notification.application.port.in.usecase.userprofile.UpdateNotificationPreferencesUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class UpdateNotificationPreferencesHandler
    extends AbstractHandler<UpdateNotificationPreferencesCommand, Void> {
    
    protected UpdateNotificationPreferencesHandler(UpdateNotificationPreferencesUseCasePort useCase) {
        super(useCase, UpdateNotificationPreferencesCommand.class);
    }
}
