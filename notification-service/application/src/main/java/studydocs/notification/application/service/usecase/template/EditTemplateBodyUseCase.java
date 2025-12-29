package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.template.EditTemplateBodyCommand;
import studydocs.notification.application.port.in.usecase.template.EditTemplateBodyUseCasePort;
import studydocs.notification.application.service.builder.TemplateContentBuilder;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
public class EditTemplateBodyUseCase implements EditTemplateBodyUseCasePort {
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final TemplateContentBuilder templateContentBuilder;

    @Override
    public Void execute(EditTemplateBodyCommand params) {
        var templateData = templateContentBuilder.build(null, params.newBody());
        var template = notificationTemplateRepository.getById(params.templateId());
        template.editBody(templateData.body());
        notificationTemplateRepository.save(template);
        return null;
    }

}
