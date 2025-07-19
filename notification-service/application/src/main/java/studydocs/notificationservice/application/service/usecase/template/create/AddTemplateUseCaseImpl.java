package studydocs.notificationservice.application.service.usecase.template.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.create.AddTemplateInputModel;
import studydocs.notificationservice.application.port.input.usecase.template.create.AddTemplateUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;

@Service
@RequiredArgsConstructor
public class AddTemplateUseCaseImpl implements AddTemplateUseCase {
    private final NotificationTemplateRepositoryPort repository;
    @Override
    public void execute(AddTemplateInputModel inputModel) {
        var notificationTemplate = inputModel.toDomain();
        repository.save(notificationTemplate);
    }
}
