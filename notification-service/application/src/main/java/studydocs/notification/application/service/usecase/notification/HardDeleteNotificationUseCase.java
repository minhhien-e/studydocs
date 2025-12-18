package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.HardDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.HardDeleteNotificationUseCasePort;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;

@Service
@RequiredArgsConstructor
public class HardDeleteNotificationUseCase implements HardDeleteNotificationUseCasePort {
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationAccessPolicy notificationPolicy;

    @Override
    public Void execute(HardDeleteNotificationCommand params) {
        var recipient = recipientRepository.getByNotificationIdAndRecipientId(
                params.notificationId(),
                params.requesterId()
        );
        notificationPolicy.checkCanAccess(recipient, params.requesterId());
        recipientRepository.deleteByNotificationIdAndRecipientId(recipient.getNotificationId(), params.requesterId());
        return null;
    }
}
