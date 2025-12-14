package studydocs.notification.application.port.in.usecase.userprofile;

import studydocs.notification.application.dto.command.userprofile.RegisterFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface RegisterFcmTokenUseCasePort extends UseCase<Void, RegisterFcmTokenCommand> {
}
