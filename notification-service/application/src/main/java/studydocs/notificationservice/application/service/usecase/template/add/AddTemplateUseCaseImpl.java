package studydocs.notificationservice.application.service.usecase.template.add;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.inputmodel.template.add.AddTemplateInputModel;
import studydocs.notificationservice.application.port.input.usecase.template.add.AddTemplateUseCase;
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
