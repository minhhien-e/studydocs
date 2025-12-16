package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.command.template.EditTemplateSubjectCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateSubjectUseCasePort;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class EditTemplateSubjectUseCase implements EditTemplateSubjectUseCasePort {
    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    public Void execute(EditTemplateSubjectCommand params) {
        var template = notificationTemplateRepository.getById(params.templateId());
        template.editSubject(params.newSubject());
        notificationTemplateRepository.save(template);
        return null;
    }
}
