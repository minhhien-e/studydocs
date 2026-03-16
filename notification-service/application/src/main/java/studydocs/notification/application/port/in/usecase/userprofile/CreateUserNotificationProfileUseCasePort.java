package studydocs.notification.application.port.in.usecase.userprofile;

import studydocs.notification.application.dto.command.userprofile.CreateUserNotificationProfileCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface CreateUserNotificationProfileUseCasePort extends UseCase<Void, CreateUserNotificationProfileCommand> {
}
