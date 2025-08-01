package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateNameInputModel;
import studydocs.notificationservice.application.port.input.usecase.template.update.UpdateTemplateNameUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

@Service
@RequiredArgsConstructor
public class UpdateTemplateNameUseCaseImpl implements UpdateTemplateNameUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public void execute(UpdateTemplateNameInputModel inputModel) {
        var notificationTemplate = repository.findByName(inputModel.getName())
                .orElseThrow(() -> new TemplateNotFoundException(inputModel.getName()));
        notificationTemplate.updateName(inputModel.getNewName());
        long modifierCount = repository.updateName(notificationTemplate.getId(), notificationTemplate.getName().getValue());
        if (modifierCount <= 0)
            throw new UpdateFailedException();
    }
}
