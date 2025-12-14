package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.UpdateEmailCommand;
import studydocs.notification.application.port.in.usecase.userprofile.UpdateEmailUseCasePort;

@Component
@RequiredArgsConstructor
public class UpdateEmailCommandHandler implements RequestHandler<BusRequestWrapper<UpdateEmailCommand>, Void> {
    private final UpdateEmailUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<UpdateEmailCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return UpdateEmailCommand.class;
    }
}
