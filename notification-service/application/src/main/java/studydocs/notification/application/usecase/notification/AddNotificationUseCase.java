package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.bus.MediatorBusPort;
import studydocs.notification.application.port.in.renderer.TemplateRenderer;
import studydocs.notification.application.port.in.usecase.notification.AddNotificationUseCasePort;
import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationRepository;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

@Service
@RequiredArgsConstructor
public class AddNotificationUseCase implements AddNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationTemplateRepository templateRepository;
    private final NotificationSendPolicy notificationSendPolicy;
    private final TemplateRenderer templateRenderer;
    private final MediatorBusPort mediatorBus;

    @Override
    public Void execute(AddNotificationCommand params) {
        notificationSendPolicy.ensureCanCreate(params.senderId(), params.templateId());
        
        // Get template for rendering
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
                params.category(),
                snapshotSubject,
                snapshotBody
        );
        notificationRepository.save(notification);
        
        if (params.recipients() != null && !params.recipients().isEmpty()) {
            params.recipients().forEach(recipient -> {
                var command = ReceiveNotificationCommand.builder()
                        .notificationId(notification.getId())
                        .recipientId(recipient.recipientId())
                        .subjectData(recipient.subjectData())
                        .bodyData(recipient.bodyData())
                        .build();
                
                mediatorBus.send(command);
            });
        }
        
        return null;
    }
}
