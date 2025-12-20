package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.SendNotificationCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;
import studydocs.notification.infrastructure.adapter.bus.handler.base.AbstractHandler;
@Component
public class SendNotificationHandler extends AbstractHandler<SendNotificationCommand, Void> {
    protected SendNotificationHandler(UseCase<Void, SendNotificationCommand> useCase) {
        super(useCase, SendNotificationCommand.class);
    }
}
