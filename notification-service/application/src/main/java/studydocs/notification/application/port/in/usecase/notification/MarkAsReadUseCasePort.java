package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.command.notification.MarkAsReadCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface MarkAsReadUseCasePort extends UseCase<Void, MarkAsReadCommand>{
}
