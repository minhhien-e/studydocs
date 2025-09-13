package studydocs.notificationservice.application.service.usecase.template.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.create.AddTemplateInput;
import studydocs.notificationservice.application.usecase.template.create.AddTemplateUseCase;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class AddTemplateUseCaseImpl implements AddTemplateUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public void execute(AddTemplateInput inputModel) {
        var notificationTemplate = inputModel.toDomain();
        repository.save(notificationTemplate);
    }
}
