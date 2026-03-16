package studydocs.notification.application.service.usecase.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.template.RenameTemplateCommand;
import studydocs.notification.application.port.in.usecase.template.RenameTemplateUseCasePort;
import studydocs.notification.domain.policy.UniqueNotificationTemplatePolicy;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
public class RenameTemplateUseCase implements RenameTemplateUseCasePort {
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final UniqueNotificationTemplatePolicy uniqueNotificationTemplatePolicy;

    @Override
    public Void execute(RenameTemplateCommand params) {
        var template = notificationTemplateRepository.getById(params.templateId());
        uniqueNotificationTemplatePolicy.checkNameUnique(params.newName());
        template.rename(params.newName());
        notificationTemplateRepository.save(template);
        return null;
    }
}
