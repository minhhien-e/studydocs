package studydocs.notificationservice.application.service.usecase.template.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByNameInput;
import studydocs.notificationservice.application.dto.output.TemplateDto;
import studydocs.notificationservice.application.port.repository.TemplateReadRepositoryPort;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByNameUseCase;

@Service
@RequiredArgsConstructor
@Transactional
public class GetTemplateByNameUseCaseImpl implements GetTemplateByNameUseCase {
    private final TemplateReadRepositoryPort repository;

    @Override
    public TemplateDto execute(GetTemplateByNameInput inputModel) {
        var templateName = inputModel.name();
        return repository.getByName(templateName);
    }
}
