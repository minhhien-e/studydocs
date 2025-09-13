package studydocs.notificationservice.application.service.usecase.recipient.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;

@Service
@RequiredArgsConstructor
@Transactional
public class MarkAsReadUseCaseImpl implements MarkAsReadUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public void execute(MarkAsReadInput inputModel) {
        var recipient = repository.getByRecipientIdAndNotificationId(inputModel.getRecipientId(), inputModel.getNotificationId());
        recipient.read();
        long modifierCol = repository.markAsRead(recipient.getRecipientId(), recipient.getNotificationId());
        if (modifierCol <= 0)
            throw new UpdateFailedException();
    }
}
