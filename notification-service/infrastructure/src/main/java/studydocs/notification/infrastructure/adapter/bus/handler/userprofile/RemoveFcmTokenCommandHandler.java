package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.RemoveFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RemoveFcmTokenUseCasePort;

@Component
@RequiredArgsConstructor
public class RemoveFcmTokenCommandHandler implements RequestHandler<BusRequestWrapper<RemoveFcmTokenCommand>, Void> {
    private final RemoveFcmTokenUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<RemoveFcmTokenCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return RemoveFcmTokenCommand.class;
    }
}
