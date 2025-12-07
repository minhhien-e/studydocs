package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.HardDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.HardDeleteNotificationUseCasePort;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HardDeleteNotificationUseCase implements HardDeleteNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationAccessPolicy notificationPolicy;

    @Override
    public Void execute(HardDeleteNotificationCommand params) {

        var notification = notificationRepository.getById(params.notificationId(), List.of(params.requesterId()));
        notificationPolicy.checkCanAccess(notification, params.requesterId());
        notification.hardDeleteRecipient(params.requesterId());
        notificationRepository.save(notification);
        return null;
    }

}
