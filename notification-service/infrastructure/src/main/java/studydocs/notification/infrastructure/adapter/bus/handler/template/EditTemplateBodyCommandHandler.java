package studydocs.notification.infrastructure.adapter.bus.handler.template;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.EditTemplateBodyCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateBodyUseCasePort;

@Component
@RequiredArgsConstructor
public class EditTemplateBodyCommandHandler implements RequestHandler<BusRequestWrapper<EditTemplateBodyCommand>, Void> {
    private final EditTemplateBodyUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<EditTemplateBodyCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return EditTemplateBodyCommand.class;
    }
}
