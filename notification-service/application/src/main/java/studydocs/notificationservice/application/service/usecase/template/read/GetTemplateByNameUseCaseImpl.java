package studydocs.notificationservice.application.service.usecase.template.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByNameInput;
import studydocs.notificationservice.application.dto.output.template.TemplateOutput;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByNameUseCase;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

@Service
@RequiredArgsConstructor
public class GetTemplateByNameUseCaseImpl implements GetTemplateByNameUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public TemplateOutput execute(GetTemplateByNameInput inputModel) {
        var notificationTemplate = repository.findByName(inputModel.name())
                .orElseThrow(() -> new TemplateNotFoundException(inputModel.name()));
        return TemplateOutput.toOutputModel(notificationTemplate);

    }
}
