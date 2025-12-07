package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.MarkAsReadCommand;
import studydocs.notification.application.port.in.usecase.notification.MarkAsReadNotificationUseCasePort;

@Component
@RequiredArgsConstructor
public class MarkAsReadCommandHandler implements RequestHandler<BusRequestWrapper<MarkAsReadCommand>, Void> {
    private final MarkAsReadNotificationUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<MarkAsReadCommand> request) {
        return useCase.execute(request.request());
    }
    @Override
    public Class<?> getRequestClass() {
        return MarkAsReadCommand.class;
    }
}
