package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.provider.NotificationDataProvider;
import studydocs.notification.application.port.in.renderer.TemplateRenderer;
import studydocs.notification.application.port.in.usecase.notification.ReceiveNotificationUseCasePort;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;
import studydocs.notification.domain.repository.NotificationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReceiveNotificationUseCase implements ReceiveNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationSendPolicy notificationSendPolicy;
    private final TemplateRenderer templateRenderer;
    private final List<NotificationDataProvider> dataProviders;

    @Override
    public Void execute(ReceiveNotificationCommand params) {
        notificationSendPolicy.ensureCanSend(params.recipientId());
        
        var notification = notificationRepository.getById(params.notificationId());

        String subjectTemplate = notification.getSnapshotSubject().value();
        String bodyTemplate = notification.getSnapshotBody().value();
        String combinedTemplate = subjectTemplate + bodyTemplate;

        Map<String, String> model = new HashMap<>();
        for (NotificationDataProvider provider : dataProviders) {
            if (provider.isNeeded(combinedTemplate)) {
                Map<String, Object> data = provider.getData(params.recipientId());
                data.forEach((key, value) -> model.put(key, String.valueOf(value)));
            }
        }

        String renderedSubject = templateRenderer.render(subjectTemplate, model);
        String renderedBody = templateRenderer.render(bodyTemplate, model);

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
