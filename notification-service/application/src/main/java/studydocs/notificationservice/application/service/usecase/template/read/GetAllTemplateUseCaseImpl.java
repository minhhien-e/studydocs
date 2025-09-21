package studydocs.notificationservice.application.service.usecase.template.read;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.read.GetAllTemplateInput;
import studydocs.notificationservice.application.dto.output.TemplateOutput;
import studydocs.notificationservice.application.usecase.template.read.GetAllTemplateUseCase;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetAllTemplateUseCaseImpl implements GetAllTemplateUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public List<TemplateOutput> execute(GetAllTemplateInput inputModel) {
        return repository.findAll().stream().map(TemplateOutput::toOutput).toList();
    }
}
