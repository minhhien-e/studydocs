package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.ReceiveNotificationUseCasePort;
import studydocs.notification.domain.entity.NotificationRecipient;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiveNotificationUseCase implements ReceiveNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationSendPolicy notificationSendPolicy;

    @Override
    public Void execute(ReceiveNotificationCommand params) {
        var notification = notificationRepository.getById(params.notificationId(), List.of(params.recipientId()));
        notificationSendPolicy.ensureCanSend(List.of(params.recipientId()));
        var notificationRecipient = NotificationRecipient.create(params.recipientId(), params.notificationId(), params.personalizedData());
        notification.addRecipient(notificationRecipient);
        notificationRepository.save(notification);
        return null;
    }
}
