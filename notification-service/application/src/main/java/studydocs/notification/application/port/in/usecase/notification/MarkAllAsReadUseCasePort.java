package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.command.notification.MarkAllAsReadCommand;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface MarkAllAsReadUseCasePort extends UseCase<Void, MarkAllAsReadCommand>{
}
