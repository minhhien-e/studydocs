package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.UpdatePhoneNumberCommand;
import studydocs.notification.application.port.in.usecase.userprofile.UpdatePhoneNumberUseCasePort;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class UpdatePhoneNumberHandler
    extends AbstractHandler<UpdatePhoneNumberCommand, Void> {
    
    protected UpdatePhoneNumberHandler(UpdatePhoneNumberUseCasePort useCase) {
        super(useCase, UpdatePhoneNumberCommand.class);
    }
}
