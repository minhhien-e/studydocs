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

@Service
@RequiredArgsConstructor
public class ReceiveNotificationUseCase implements ReceiveNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationSendPolicy notificationSendPolicy;
    private final TemplateRenderer templateRenderer;

    @Override
    public Void execute(ReceiveNotificationCommand params) {
        notificationSendPolicy.ensureCanSend(params.recipientId());
        
        var notification = notificationRepository.getById(params.notificationId());

        String renderedSubject = templateRenderer.render(
                notification.getSnapshotSubject().value(),
                params.subjectData()
        );
        String renderedBody = templateRenderer.render(
                notification.getSnapshotBody().value(),
                params.bodyData()
        );
        
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
