package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.dto.input.template.update.concrete.UpdateTemplateSubjectInput;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateSubjectUseCase;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

@Service
@RequiredArgsConstructor
public class UpdateTemplateSubjectUseCaseImpl implements UpdateTemplateSubjectUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public void execute(UpdateTemplateSubjectInput inputModel) {
        var notificationTemplate = repository.findByName(inputModel.getName())
                .orElseThrow(() -> new TemplateNotFoundException(inputModel.getName()));
        notificationTemplate.updateSubject(inputModel.getNewSubject());
        long modifierCount = repository.updateSubject(notificationTemplate.getId(),
                notificationTemplate.getSubjectTemplate().value());
        if (modifierCount <= 0)
            throw new UpdateFailedException();
    }
}
