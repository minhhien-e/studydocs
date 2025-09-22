package studydocs.notificationservice.application.service.usecase.notification.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.notification.delete.SoftDeleteNotificationInput;
import studydocs.notificationservice.application.usecase.notificaton.delete.SoftDeleteNotificationUseCase;
import studydocs.notificationservice.domain.repository.RecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class SoftDeleteNotificationUseCaseImpl implements SoftDeleteNotificationUseCase {
    private final RecipientRepositoryPort notificationRecipientRepository;

    @Override
    public void execute(SoftDeleteNotificationInput inputModel) {
        //Load dữ liệu
        var notificationId = inputModel.notificationId();
        var recipientId = inputModel.requesterId();
        var userNotificationAggregate = notificationRecipientRepository.getByRecipientIdAndNotificationId(recipientId, notificationId);
        // Xử lý logic
        var recipient = userNotificationAggregate.softDeleteNotification(notificationId);
        // Gọi repository
        notificationRecipientRepository.updateDeletedAt(recipient);
    }
}
