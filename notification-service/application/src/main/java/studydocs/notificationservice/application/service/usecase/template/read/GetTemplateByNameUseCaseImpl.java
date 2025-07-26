package studydocs.notificationservice.application.service.usecase.template.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.read.GetTemplateByNameInputModel;
import studydocs.notificationservice.application.port.input.dto.outputmodel.template.TemplateOutputModel;
import studydocs.notificationservice.application.port.input.usecase.template.read.GetTemplateByNameUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

@Service
@RequiredArgsConstructor
public class GetTemplateByNameUseCaseImpl implements GetTemplateByNameUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public TemplateOutputModel execute(GetTemplateByNameInputModel inputModel) {
        var notificationTemplate = repository.findByName(inputModel.name())
                .orElseThrow(() -> new TemplateNotFoundException(inputModel.name()));
        return TemplateOutputModel.toOutputModel(notificationTemplate);

    }
}
