package studydocs.notification.infrastructure.adapter.bus.handler.template;

import io.github.mediatR.api.RequestHandler;
import io.github.mediatR.core.BusRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.template.EditTemplateSubjectCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateSubjectUseCasePort;

@Component
@RequiredArgsConstructor
public class EditTemplateSubjectCommandHandler implements RequestHandler<BusRequestWrapper<EditTemplateSubjectCommand>, Void> {
    private final EditTemplateSubjectUseCasePort useCase;

    @Override
    public Void execute(BusRequestWrapper<EditTemplateSubjectCommand> request) {
        return useCase.execute(request.request());
    }

    @Override
    public Class<?> getRequestClass() {
        return EditTemplateSubjectCommand.class;
    }
}
