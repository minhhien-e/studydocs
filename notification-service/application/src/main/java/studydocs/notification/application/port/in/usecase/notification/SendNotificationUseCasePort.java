package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.command.notification.SendNotificationCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface SendNotificationUseCasePort extends UseCase<Void, SendNotificationCommand> {
}
