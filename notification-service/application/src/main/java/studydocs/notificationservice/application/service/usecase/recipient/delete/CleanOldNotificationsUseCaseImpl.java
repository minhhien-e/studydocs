package studydocs.notificationservice.application.service.usecase.recipient.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.usecase.recipient.delete.CleanOldNotificationsUseCase;
import studydocs.notificationservice.domain.repository.RecipientRepositoryPort;
import studydocs.notificationservice.domain.service.NotificationPolicy;

@Service
@RequiredArgsConstructor
public class CleanOldNotificationsUseCaseImpl implements CleanOldNotificationsUseCase {
    private final RecipientRepositoryPort repository;

    @Override
    public void execute() {
        var recipients = repository.findAll();
        recipients.forEach((recipient) -> {
            if (NotificationPolicy.isTrashExpired(recipient))
                repository.deleteById(recipient.getId());
        });
    }
}
