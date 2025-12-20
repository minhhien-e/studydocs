package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.RegisterFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RegisterFcmTokenUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class RegisterFcmTokenHandler
    extends AbstractHandler<RegisterFcmTokenCommand, Void> {
    
    protected RegisterFcmTokenHandler(RegisterFcmTokenUseCasePort useCase) {
        super(useCase, RegisterFcmTokenCommand.class);
    }
}
