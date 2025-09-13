package studydocs.notificationservice.application.service.usecase.notification.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.notification.delete.SoftDeleteNotificationInput;
import studydocs.notificationservice.application.usecase.notificaton.delete.SoftDeleteNotificationUseCase;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class SoftDeleteNotificationUseCaseImpl implements SoftDeleteNotificationUseCase {
    private final NotificationRecipientRepositoryPort notificationRecipientRepository;

    @Override
    public void execute(SoftDeleteNotificationInput inputModel) {
        var recipient = notificationRecipientRepository.getByRecipientIdAndNotificationId(inputModel.requesterId(), inputModel.notificationId());
        recipient.delete();
        notificationRecipientRepository.updateDeletedAt(recipient);
    }
}
