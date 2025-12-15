package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.UpdateNotificationPreferencesCommand;
import studydocs.notification.application.port.in.usecase.userprofile.UpdateNotificationPreferencesUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class UpdateNotificationPreferencesCommandHandler 
    extends AbstractHandler<UpdateNotificationPreferencesCommand, Void, UpdateNotificationPreferencesUseCasePort> {
    
    protected UpdateNotificationPreferencesCommandHandler(UpdateNotificationPreferencesUseCasePort useCase) {
        super(useCase, UpdateNotificationPreferencesCommand.class);
    }
}
