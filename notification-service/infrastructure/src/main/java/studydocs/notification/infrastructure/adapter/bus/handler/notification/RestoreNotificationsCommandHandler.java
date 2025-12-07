package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.RestoreNotificationsCommand;
import studydocs.notification.application.port.in.usecase.notification.RestoreNotificationsUseCasePort;

@Component
@RequiredArgsConstructor
public class RestoreNotificationsCommandHandler implements RequestHandler<BusRequestWrapper<RestoreNotificationsCommand>, Void> {
    private final RestoreNotificationsUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<RestoreNotificationsCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return RestoreNotificationsCommand.class;
    }
}
