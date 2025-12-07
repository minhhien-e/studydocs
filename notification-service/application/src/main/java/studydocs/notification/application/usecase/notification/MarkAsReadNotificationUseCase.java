package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.MarkAsReadCommand;
import studydocs.notification.application.port.in.usecase.notification.MarkAsReadNotificationUseCasePort;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkAsReadNotificationUseCase implements MarkAsReadNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationAccessPolicy notificationPolicy;

    @Override
    public Void execute(MarkAsReadCommand params) {
        var notification = notificationRepository.getById(params.notificationId(), List.of(params.recipientId()));
        notificationPolicy.checkCanAccess(notification, params.recipientId());
        notification.readNotification(params.recipientId());
        notificationRepository.save(notification);
        return null;
    }

}
