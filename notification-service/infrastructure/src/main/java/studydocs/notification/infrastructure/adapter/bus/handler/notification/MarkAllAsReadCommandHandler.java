package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.MarkAllAsReadCommand;
import studydocs.notification.application.port.in.usecase.notification.MarkAllAsReadNotificationUseCasePort;

@Component
@RequiredArgsConstructor
public class MarkAllAsReadCommandHandler implements RequestHandler<BusRequestWrapper<MarkAllAsReadCommand>, Void> {
    private final MarkAllAsReadNotificationUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<MarkAllAsReadCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return MarkAllAsReadCommand.class;
    }
}
