package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.template.EditTemplateBodyCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateBodyUseCasePort;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
public class EditTemplateBodyUseCase implements EditTemplateBodyUseCasePort {
    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    public Void execute(EditTemplateBodyCommand params) {
        var template = notificationTemplateRepository.getById(params.templateId());
        template.editBody(params.newBody());
        notificationTemplateRepository.save(template);
        return null;
    }

}
