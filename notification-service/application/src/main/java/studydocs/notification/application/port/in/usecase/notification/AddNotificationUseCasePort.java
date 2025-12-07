package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface AddNotificationUseCasePort extends UseCase<Void,AddNotificationCommand> {
}
