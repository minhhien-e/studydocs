package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.ReceiveNotificationUseCasePort;

@Component
@RequiredArgsConstructor
public class ReceiveNotificationCommandHandler implements RequestHandler<BusRequestWrapper<ReceiveNotificationCommand>, Void> {
    private final ReceiveNotificationUseCasePort receiveNotificationUseCasePort;

    @Override
    public Void execute(BusRequestWrapper<ReceiveNotificationCommand> request) {
        return receiveNotificationUseCasePort.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return ReceiveNotificationCommand.class;
    }
}
