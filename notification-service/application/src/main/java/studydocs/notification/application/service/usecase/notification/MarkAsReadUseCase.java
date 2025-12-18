package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.MarkAsReadCommand;
import studydocs.notification.application.port.in.usecase.notification.MarkAsReadUseCasePort;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;

@Service
@RequiredArgsConstructor
public class MarkAsReadUseCase implements MarkAsReadUseCasePort {
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationAccessPolicy notificationPolicy;

    @Override
    public Void execute(MarkAsReadCommand params) {
        var recipient = recipientRepository.getByNotificationIdAndRecipientId(
                params.notificationId(),
                params.recipientId()
        );
        notificationPolicy.checkCanAccess(recipient, params.recipientId());
        recipient.markAsRead();
        recipientRepository.save(recipient);
        return null;
    }
}
