package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.SoftDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.SoftDeleteNotificationUseCasePort;

@Component
@RequiredArgsConstructor
public class SoftDeleteNotificationCommandHandler implements RequestHandler<BusRequestWrapper<SoftDeleteNotificationCommand>, Void> {
    private final SoftDeleteNotificationUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<SoftDeleteNotificationCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return SoftDeleteNotificationCommand.class;
    }
}
