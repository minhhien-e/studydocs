package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.command.notification.HardDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface HardDeleteNotificationUseCasePort extends UseCase<Void, HardDeleteNotificationCommand> {
}
