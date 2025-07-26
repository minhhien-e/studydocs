package studydocs.notificationservice.application.service.usecase.template.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.update.UpdateTemplateDescriptionInputModel;
import studydocs.notificationservice.application.port.input.usecase.template.update.UpdateTemplateDescriptionUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

@Service
@RequiredArgsConstructor
public class UpdateTemplateDescriptionUseCaseImpl implements UpdateTemplateDescriptionUseCase {
    private final NotificationTemplateRepositoryPort repository;

    @Override
    public void execute(UpdateTemplateDescriptionInputModel inputModel) {
        var notificationTemplate = repository.findByName(inputModel.templateName())
                .orElseThrow(() -> new TemplateNotFoundException(inputModel.templateName()));
        notificationTemplate.updateDescription(inputModel.newDescription());
        long modifierCount = repository.updateDescription(notificationTemplate.getId(),
                notificationTemplate.getDescription().orElse(""));
        if (modifierCount <= 0)
            throw new UpdateFailedException();
    }
}
