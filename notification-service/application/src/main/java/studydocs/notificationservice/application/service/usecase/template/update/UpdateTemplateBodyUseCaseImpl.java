package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.template.update.UpdateTemplateBodyInput;
import studydocs.notificationservice.application.usecase.template.update.UpdateTemplateBodyUseCase;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateTemplateBodyUseCaseImpl implements UpdateTemplateBodyUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public void execute(UpdateTemplateBodyInput inputModel) {
        var notificationTemplate = repository.findByName(inputModel.getName())
                .orElseThrow(() -> new TemplateNotFoundException(inputModel.getName()));
        notificationTemplate.updateBody(inputModel.getNewBody());
        long modifierCount = repository.updateBody(notificationTemplate.getId(),
                notificationTemplate.getBodyTemplate().value());
        if (modifierCount <= 0)
            throw new UpdateFailedException();
    }
}
