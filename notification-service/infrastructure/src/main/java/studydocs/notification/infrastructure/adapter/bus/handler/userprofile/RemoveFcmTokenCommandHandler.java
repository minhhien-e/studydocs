package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.RemoveFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RemoveFcmTokenUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class RemoveFcmTokenCommandHandler 
    extends AbstractHandler<RemoveFcmTokenCommand, Void, RemoveFcmTokenUseCasePort> {
    
    protected RemoveFcmTokenCommandHandler(RemoveFcmTokenUseCasePort useCase) {
        super(useCase, RemoveFcmTokenCommand.class);
    }
}
