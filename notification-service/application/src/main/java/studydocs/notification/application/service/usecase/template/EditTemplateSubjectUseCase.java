package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.template.EditTemplateSubjectCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateSubjectUseCasePort;
import studydocs.notification.application.service.builder.TemplateContentBuilder;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
public class EditTemplateSubjectUseCase implements EditTemplateSubjectUseCasePort {
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final TemplateContentBuilder templateContentBuilder;

    @Override
    public Void execute(EditTemplateSubjectCommand params) {
        var templateData = templateContentBuilder.build(params.newSubject(), null);
        var template = notificationTemplateRepository.getById(params.templateId());
        template.editSubject(templateData.subject());
        notificationTemplateRepository.save(template);
        return null;
    }
}
