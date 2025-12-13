package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.SoftDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.SoftDeleteNotificationUseCasePort;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;

@Service
@RequiredArgsConstructor
public class SoftDeleteNotificationUseCase implements SoftDeleteNotificationUseCasePort {
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationAccessPolicy notificationPolicy;

    @Override
    public Void execute(SoftDeleteNotificationCommand params) {
        var recipient = recipientRepository.getByNotificationIdAndRecipientId(
                params.notificationId(),
                params.requesterId()
        );
        notificationPolicy.checkCanAccess(recipient, params.requesterId());
        recipient.softDelete();
        recipientRepository.save(recipient);
        return null;
    }
}
