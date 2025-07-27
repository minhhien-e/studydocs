package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateBodyInputModel;
import studydocs.notificationservice.application.port.input.usecase.template.update.UpdateTemplateBodyUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

@Service
@RequiredArgsConstructor
public class UpdateTemplateBodyUseCaseImpl implements UpdateTemplateBodyUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public void execute(UpdateTemplateBodyInputModel inputModel) {
        var notificationTemplate = repository.findByName(inputModel.getName())
                .orElseThrow(() -> new TemplateNotFoundException(inputModel.getName()));
        notificationTemplate.updateBody(inputModel.getNewBody());
        long modifierCount = repository.updateBody(notificationTemplate.getId(),
                notificationTemplate.getBodyTemplate().value());
        if (modifierCount <= 0)
            throw new UpdateFailedException();
    }
}
