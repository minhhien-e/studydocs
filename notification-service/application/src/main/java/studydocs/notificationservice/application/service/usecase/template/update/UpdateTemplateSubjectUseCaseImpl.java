package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.concrete.UpdateTemplateSubjectInputModel;
import studydocs.notificationservice.application.port.input.usecase.template.update.UpdateTemplateSubjectUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

@Service
@RequiredArgsConstructor
public class UpdateTemplateSubjectUseCaseImpl implements UpdateTemplateSubjectUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public void execute(UpdateTemplateSubjectInputModel inputModel) {
        var notificationTemplate = repository.findByName(inputModel.getName())
                .orElseThrow(() -> new TemplateNotFoundException(inputModel.getName()));
        notificationTemplate.updateSubject(inputModel.getNewSubject());
        long modifierCount = repository.updateSubject(notificationTemplate.getId(),
                notificationTemplate.getSubjectTemplate().value());
        if (modifierCount <= 0)
            throw new UpdateFailedException();
    }
}
