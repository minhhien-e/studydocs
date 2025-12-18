package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.template.EditTemplateDescriptionCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateDescriptionUseCasePort;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
public class EditTemplateDescriptionUseCase implements EditTemplateDescriptionUseCasePort {
    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    public Void execute(EditTemplateDescriptionCommand params) {
        var template = notificationTemplateRepository.getById(params.templateId());
        template.editDescription(params.newDescription());
        notificationTemplateRepository.save(template);
        return null;
    }

}
