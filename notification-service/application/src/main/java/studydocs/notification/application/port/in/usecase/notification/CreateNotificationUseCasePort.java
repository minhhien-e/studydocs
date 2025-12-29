package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.command.notification.CreateNotificationCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

import java.util.UUID;

public interface CreateNotificationUseCasePort extends UseCase<UUID, CreateNotificationCommand> {
}
