package studydocs.notification.infrastructure.adapter.bus.handler.template;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.AddTemplateCommand;
import studydocs.notification.application.port.in.usecase.template.AddTemplateUseCasePort;

@Component
@RequiredArgsConstructor
public class AddTemplateCommandHandler implements RequestHandler<BusRequestWrapper<AddTemplateCommand>, Void> {
    private final AddTemplateUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<AddTemplateCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return AddTemplateCommand.class;
    }
}
