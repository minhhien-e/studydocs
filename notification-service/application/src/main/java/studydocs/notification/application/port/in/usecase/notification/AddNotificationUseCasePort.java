package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

import java.util.UUID;

public interface AddNotificationUseCasePort extends UseCase<UUID,AddNotificationCommand> {
}
