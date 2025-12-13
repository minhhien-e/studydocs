package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.renderer.TemplateRenderer;
import studydocs.notification.application.port.in.usecase.notification.ReceiveNotificationUseCasePort;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;
import studydocs.notification.domain.repository.NotificationRepository;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
public class ReceiveNotificationUseCase implements ReceiveNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationTemplateRepository templateRepository;
    private final NotificationSendPolicy notificationSendPolicy;
    private final TemplateRenderer templateRenderer;

    @Override
    public Void execute(ReceiveNotificationCommand params) {
        notificationSendPolicy.ensureCanSend(params.recipientId());
        
        // Get notification metadata and template
        var notification = notificationRepository.getById(params.notificationId());
        var template = templateRepository.getById(notification.getTemplateId());
        
        // Render subject and body with separate data
        String renderedSubject = templateRenderer.render(
                template.getSubjectTemplate().value(),
                params.subjectData()
        );
        String renderedBody = templateRenderer.render(
                template.getBodyTemplate().value(),
                params.bodyData()
        );
        
        // Create and save recipient
        var recipient = NotificationRecipient.create(
                params.notificationId(),
                params.recipientId(),
                renderedSubject,
                renderedBody
        );
        recipientRepository.save(recipient);
        
        return null;
    }
}
