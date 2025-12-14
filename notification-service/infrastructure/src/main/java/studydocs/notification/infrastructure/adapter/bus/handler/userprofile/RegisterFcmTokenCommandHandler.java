package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.RegisterFcmTokenCommand;
import studydocs.notification.application.port.in.usecase.userprofile.RegisterFcmTokenUseCasePort;

@Component
@RequiredArgsConstructor
public class RegisterFcmTokenCommandHandler implements RequestHandler<BusRequestWrapper<RegisterFcmTokenCommand>, Void> {
    private final RegisterFcmTokenUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<RegisterFcmTokenCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return RegisterFcmTokenCommand.class;
    }
}
