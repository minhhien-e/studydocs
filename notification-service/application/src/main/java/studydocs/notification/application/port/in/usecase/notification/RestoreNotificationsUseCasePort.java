package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.command.notification.RestoreNotificationsCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface RestoreNotificationsUseCasePort extends UseCase<Void, RestoreNotificationsCommand>{
}
