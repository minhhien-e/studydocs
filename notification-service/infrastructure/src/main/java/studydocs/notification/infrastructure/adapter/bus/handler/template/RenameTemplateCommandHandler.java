package studydocs.notification.infrastructure.adapter.bus.handler.template;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.RenameTemplateCommand;
import studydocs.notification.application.port.in.usecase.template.RenameTemplateUseCasePort;

@Component
@RequiredArgsConstructor
public class RenameTemplateCommandHandler implements RequestHandler<BusRequestWrapper<RenameTemplateCommand>, Void> {
    private final RenameTemplateUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<RenameTemplateCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return RenameTemplateCommand.class;
    }
}
