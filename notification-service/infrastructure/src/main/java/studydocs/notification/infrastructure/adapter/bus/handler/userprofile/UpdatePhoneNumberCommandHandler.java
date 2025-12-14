package studydocs.notification.infrastructure.adapter.bus.handler.userprofile;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.userprofile.UpdatePhoneNumberCommand;
import studydocs.notification.application.port.in.usecase.userprofile.UpdatePhoneNumberUseCasePort;

@Component
@RequiredArgsConstructor
public class UpdatePhoneNumberCommandHandler implements RequestHandler<BusRequestWrapper<UpdatePhoneNumberCommand>, Void> {
    private final UpdatePhoneNumberUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<UpdatePhoneNumberCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return UpdatePhoneNumberCommand.class;
    }
}
