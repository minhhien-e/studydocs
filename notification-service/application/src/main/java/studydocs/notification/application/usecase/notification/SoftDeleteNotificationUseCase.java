package studydocs.notification.application.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.SoftDeleteNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.SoftDeleteNotificationUseCasePort;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoftDeleteNotificationUseCase implements SoftDeleteNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationAccessPolicy notificationPolicy;

    @Override
    public Void execute(SoftDeleteNotificationCommand params) {
        var notification = notificationRepository.getById(params.notificationId(), List.of(params.requesterId()));
        notificationPolicy.checkCanAccess(notification, params.requesterId());
        notification.softDeleteNotification(params.requesterId());
        notificationRepository.save(notification);
        return null;
    }

}
