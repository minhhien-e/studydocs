package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.RemoveFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RemoveFcmTokenUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class RemoveFcmTokenHandler
    extends AbstractHandler<RemoveFcmTokenCommand, Void> {
    
    protected RemoveFcmTokenHandler(RemoveFcmTokenUseCasePort useCase) {
        super(useCase, RemoveFcmTokenCommand.class);
    }
}
