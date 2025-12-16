package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.command.notification.RestoreNotificationsCommand;
import studydocs.notification.application.port.in.usecase.notification.RestoreNotificationsUseCasePort;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RestoreNotificationsUseCase implements RestoreNotificationsUseCasePort {
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationAccessPolicy notificationPolicy;

    @Override
    public Void execute(RestoreNotificationsCommand params) {
        params.notificationIds().forEach(notificationId -> {
            var recipient = recipientRepository.getByNotificationIdAndRecipientId(
                    notificationId,
                    params.recipientId()
            );
            if (recipient != null) {
                notificationPolicy.checkCanAccess(recipient, params.recipientId());
                recipient.restore();
                recipientRepository.save(recipient);
            }
        });
        return null;
    }
}
