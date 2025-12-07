package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.RestoreNotificationsCommand;
import studydocs.notification.application.port.in.usecase.notification.RestoreNotificationsUseCasePort;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestoreNotificationsUseCase implements RestoreNotificationsUseCasePort {
    private final NotificationRepository notificationRepository;
    private final studydocs.notification.application.port.out.repository.NotificationRepository notificationQueryRepository;
    private final NotificationAccessPolicy notificationPolicy;

    @Override
    public Void execute(RestoreNotificationsCommand params) {
        UUID recipientId = params.recipientId();
        int batchSize = 100;
        LocalDateTime lastSeenCreatedAt = LocalDateTime.now();
        List<UUID> batch;
        do {
            batch = notificationQueryRepository.getDeletedNotificationIdsByRecipientId(recipientId, batchSize, lastSeenCreatedAt);
            if (!batch.isEmpty()) {
                var notifications = notificationRepository.getByRecipientId(recipientId, batch);
                notifications.forEach(notification -> {
                    notificationPolicy.checkCanAccess(notification,recipientId);
                    notification.restoreNotification(recipientId);
                });
                notifications.forEach(notificationRepository::save);
                lastSeenCreatedAt = notifications.get(notifications.size() - 1).getCreatedAt().value();
            }
        }
        while (!batch.isEmpty());
        return null;
    }

}
