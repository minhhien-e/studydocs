package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.query.notification.GetNotificationByRecipientIdQuery;
import studydocs.notification.application.dto.readmodel.NotificationReadModel;
import studydocs.notification.application.port.in.renderer.TemplateRenderer;
import studydocs.notification.application.port.in.usecase.notification.GetNotificationByRecipientIdUseCasePort;
import studydocs.notification.application.port.out.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetNotificationByRecipientIdUseCase implements GetNotificationByRecipientIdUseCasePort {
    private final NotificationRepository notificationRepository;
    private final TemplateRenderer templateRenderer;

    @Override
    public List<NotificationReadModel> execute(GetNotificationByRecipientIdQuery params) {
        var notificationProjections = notificationRepository.getByRecipientId(params.recipientId(), params.isDeleted(), params.receivedAt(), params.limit());
        List<NotificationReadModel> results = new ArrayList<>();
        for (var notificationProjection : notificationProjections) {
            var template = notificationProjection.template();
            var recipient = notificationProjection.notificationRecipients().get(0);
            String content = templateRenderer.render(template.bodyTemplate(), notificationProjection.templateData());
            content = templateRenderer.render(content, recipient.personalizedData());
            var notification = new NotificationReadModel(
                    notificationProjection.id(),
                    "",
                    template.subjectTemplate(),
                    content,
                    notificationProjection.category(),
                    recipient.isRead(),
                    recipient.deletedAt(),
                    recipient.receivedAt()
            );
            results.add(notification);
        }
        return results;
    }

}
