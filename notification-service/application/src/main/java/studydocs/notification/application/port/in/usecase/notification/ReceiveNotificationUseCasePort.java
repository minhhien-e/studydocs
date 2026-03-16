package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface ReceiveNotificationUseCasePort extends UseCase<Void, ReceiveNotificationCommand> {
}
