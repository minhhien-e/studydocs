package studydocs.notificationservice.application.service.usecase.recipient.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.notification.NotificationNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class MarkAsReadUseCaseImpl implements MarkAsReadUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public void execute(MarkAsReadInput inputModel) {
        var recipientOptional = repository.findByRecipientIdAndNotificationId(inputModel.getRecipientId(), inputModel.getNotificationId());
        if (recipientOptional.isEmpty())
            throw new NotificationNotFoundException(inputModel.getNotificationId());
        var recipient = recipientOptional.get();
        recipient.read();
        long modifierCol = repository.markAsRead(recipient.getRecipientId(), recipient.getNotificationId());
        if (modifierCol <= 0)
            throw new UpdateFailedException();
    }
}
