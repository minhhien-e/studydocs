package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.template.AddTemplateCommand;
import studydocs.notification.application.port.in.usecase.template.AddTemplateUseCasePort;
import studydocs.notification.application.service.builder.TemplateContentBuilder;
import studydocs.notification.domain.aggregate.NotificationTemplate;
import studydocs.notification.domain.policy.UniqueNotificationTemplatePolicy;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
public class AddTemplateUseCase implements AddTemplateUseCasePort {
    private final NotificationTemplateRepository templateRepository;
    private final TemplateContentBuilder builder;
    private final UniqueNotificationTemplatePolicy policy;

    @Override
    public Void execute(AddTemplateCommand params) {
        policy.checkNameUnique(params.name());
        var templateData = builder.build(params.subjectTemplate(), params.bodyTemplate());
        var template = NotificationTemplate.create(params.name(), params.channel(), templateData.subject(), templateData.body(), params.description());
        templateRepository.save(template);
        return null;
    }
}
