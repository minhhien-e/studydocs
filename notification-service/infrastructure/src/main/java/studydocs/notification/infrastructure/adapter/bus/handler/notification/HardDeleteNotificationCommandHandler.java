package studydocs.notification.infrastructure.adapter.bus.handler.notification;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.HardDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.HardDeleteNotificationUseCasePort;

@Component
@RequiredArgsConstructor
public class HardDeleteNotificationCommandHandler implements RequestHandler<BusRequestWrapper<HardDeleteNotificationCommand>, Void> {
    private final HardDeleteNotificationUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<HardDeleteNotificationCommand> request) {
        return useCase.execute(request.request());
    }
    @Override
    public Class<?> getRequestClass() {
        return HardDeleteNotificationCommand.class;
    }
}
