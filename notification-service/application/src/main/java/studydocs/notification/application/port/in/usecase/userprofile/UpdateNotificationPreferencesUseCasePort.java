package studydocs.notification.application.port.in.usecase.userprofile;

import studydocs.notification.application.dto.command.userprofile.UpdateNotificationPreferencesCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface UpdateNotificationPreferencesUseCasePort extends UseCase<Void, UpdateNotificationPreferencesCommand> {
}
