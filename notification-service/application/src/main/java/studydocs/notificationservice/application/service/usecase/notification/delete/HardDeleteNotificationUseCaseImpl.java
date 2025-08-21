package studydocs.notificationservice.application.service.usecase.notification.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.notification.delete.HardDeleteNotificationInput;
import studydocs.notificationservice.application.usecase.notificaton.delete.HardDeleteNotificationUseCase;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class HardDeleteNotificationUseCaseImpl implements HardDeleteNotificationUseCase {
    private final NotificationRecipientRepositoryPort notificationRecipientRepository;

    @Override
    public void execute(HardDeleteNotificationInput inputModel) {
        var recipient = notificationRecipientRepository.getByRecipientIdAndNotificationId(inputModel.requesterId(), inputModel.notificationId());
        notificationRecipientRepository.deleteById(recipient.getRecipientId());
    }
}
