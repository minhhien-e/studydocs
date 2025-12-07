package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.AddNotificationUseCasePort;

@Component
@RequiredArgsConstructor
public class AddNotificationCommandHandler implements RequestHandler<BusRequestWrapper<AddNotificationCommand>, Void> {
    private final AddNotificationUseCasePort addNotificationUseCase;


    @Override
    public Void execute(BusRequestWrapper<AddNotificationCommand> request) {
        return addNotificationUseCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return AddNotificationCommand.class;
    }
}
