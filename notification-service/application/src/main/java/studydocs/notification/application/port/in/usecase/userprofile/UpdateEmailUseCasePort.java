package studydocs.notification.application.port.in.usecase.userprofile;

import studydocs.notification.application.dto.command.userprofile.UpdateEmailCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface UpdateEmailUseCasePort extends UseCase<Void, UpdateEmailCommand> {
}
