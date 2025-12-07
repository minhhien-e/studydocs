package studydocs.notification.infrastructure.adapter.bus.handler.template;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.EditTemplateDescriptionCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateDescriptionUseCasePort;

@Component
@RequiredArgsConstructor
public class EditTemplateDescriptionCommandHandler implements RequestHandler<BusRequestWrapper<EditTemplateDescriptionCommand>, Void> {
    private final EditTemplateDescriptionUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<EditTemplateDescriptionCommand> request) {
        return useCase.execute(request.request());
    }
    @Override
    public Class<?> getRequestClass() {
        return EditTemplateDescriptionCommand.class;
    }
}
