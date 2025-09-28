package studydocs.notificationservice.application.service.usecase.recipient.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.update.RestoreNotificationsInput;
import studydocs.notificationservice.application.usecase.recipient.update.RestoreNotificationsUseCase;
import studydocs.notificationservice.domain.repository.RecipientRepositoryPort;

@Service
@Transactional
@RequiredArgsConstructor
public class RestoreNotificationsUseCasImpl implements RestoreNotificationsUseCase {
    private final RecipientRepositoryPort repository;

    @Override
    public void execute(RestoreNotificationsInput inputModel) {
        var userNotification = repository.findByRecipientIdAndNotificationIdList(inputModel.recipientId(), inputModel.notificationIds());
        var notificationsRestored = userNotification.restoreNotification(inputModel.notificationIds());
        repository.restore(notificationsRestored);
    }
}
