package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.AddNotificationUseCasePort;
import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.entity.NotificationRecipient;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
public class AddNotificationUseCase implements AddNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationSendPolicy notificationSendPolicy;

    @Override
    public Void execute(AddNotificationCommand params) {
        notificationSendPolicy.ensureCanCreate(params.senderId(), params.templateId());
        var notification = Notification.create(
                params.senderId(),
                params.templateId(),
                params.channel(),
                params.category(),
                params.templateData()
        );
        if (params.personalizedData() != null && !params.personalizedData().isEmpty()) {
            notificationSendPolicy.ensureCanSend(params.personalizedData().keySet().stream().toList());
            params.personalizedData().forEach(
                    (key, value) -> notification.addRecipient(
                            NotificationRecipient.create(key, notification.getId(), value)
                    )
            );
        }
        notificationRepository.save(notification);
        return null;

    }
}
