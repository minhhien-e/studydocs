package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.RegisterFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RegisterFcmTokenUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class RegisterFcmTokenCommandHandler 
    extends AbstractHandler<RegisterFcmTokenCommand, Void, RegisterFcmTokenUseCasePort> {
    
    protected RegisterFcmTokenCommandHandler(RegisterFcmTokenUseCasePort useCase) {
        super(useCase, RegisterFcmTokenCommand.class);
    }
}
