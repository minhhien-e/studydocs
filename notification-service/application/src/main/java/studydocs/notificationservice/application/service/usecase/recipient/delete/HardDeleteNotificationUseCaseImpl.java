package studydocs.notificationservice.application.service.usecase.recipient.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.delete.HardDeleteNotificationInput;
import studydocs.notificationservice.application.usecase.recipient.delete.HardDeleteNotificationUseCase;
import studydocs.notificationservice.domain.repository.RecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class HardDeleteNotificationUseCaseImpl implements HardDeleteNotificationUseCase {
    private final RecipientRepositoryPort notificationRecipientRepository;

    @Override
    public void execute(HardDeleteNotificationInput inputModel) {
        //Load dữ liệu
        var notificationId = inputModel.notificationId();
        var recipientId = inputModel.requesterId();
        var userNotificationAggregate = notificationRecipientRepository.getByRecipientIdAndNotificationId(recipientId, notificationId);
        // Xử lý logic
        userNotificationAggregate.hardDeleteNotification(notificationId);
        // Gọi repository
        notificationRecipientRepository.deleteById(recipientId);
    }
}
