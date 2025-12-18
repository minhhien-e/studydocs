package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.CreateNotificationCommand;
import studydocs.notification.application.port.in.renderer.TemplateRenderer;
import studydocs.notification.application.port.in.usecase.notification.CreateNotificationUseCasePort;
import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationRepository;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateNotificationUseCase implements CreateNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationTemplateRepository templateRepository;
    private final NotificationSendPolicy notificationSendPolicy;
    private final TemplateRenderer templateRenderer;

    public UUID execute(CreateNotificationCommand params) {
        notificationSendPolicy.ensureCanCreate(params.senderId(), params.templateId());
        var template = templateRepository.getById(params.templateId());
        String snapshotSubject = templateRenderer.render(
                template.getSubjectTemplate().value(),
                params.snapshotSubjectData()
        );
        String snapshotBody = templateRenderer.render(
                template.getBodyTemplate().value(),
                params.snapshotBodyData()
        );
        var notification = Notification.create(
                params.senderId(),
                params.templateId(),
                params.channel(),
                params.type(),
                snapshotSubject,
                snapshotBody
        );
        notificationRepository.save(notification);

        return notification.getId();
    }
}
