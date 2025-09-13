package studydocs.notificationservice.application.service.usecase.notification.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.usecase.notificaton.delete.CleanOldNotificationsUseCase;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.domain.service.NotificationCleanerPolicy;

@Service
@RequiredArgsConstructor
public class CleanOldNotificationsUseCaseImpl implements CleanOldNotificationsUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public void execute() {
        var notificationRecipients = repository.findAll();
        notificationRecipients.forEach((recipient) -> {
            if (NotificationCleanerPolicy.isExpired(recipient))
                repository.deleteById(recipient.getId());
        });
    }
}
