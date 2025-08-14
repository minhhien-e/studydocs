package studydocs.notificationservice.application.service.usecase.template.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.dto.input.template.read.GetAllTemplateInput;
import studydocs.notificationservice.application.dto.output.template.TemplateOutput;
import studydocs.notificationservice.application.usecase.template.read.GetAllTemplateUseCase;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTemplateUseCaseImpl implements GetAllTemplateUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public List<TemplateOutput> execute(GetAllTemplateInput inputModel) {
        return repository.findAll().stream().map(TemplateOutput::toOutput).toList();
    }
}
