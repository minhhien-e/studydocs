package studydocs.notification.application.port.in.usecase.userprofile;

import studydocs.notification.application.dto.command.userprofile.RemoveFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface RemoveFcmTokenUseCasePort extends UseCase<Void, RemoveFcmTokenCommand> {
}
