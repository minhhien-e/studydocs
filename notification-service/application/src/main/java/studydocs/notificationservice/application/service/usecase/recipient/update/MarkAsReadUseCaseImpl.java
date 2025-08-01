package studydocs.notificationservice.application.service.usecase.recipient.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAsReadInputModel;
import studydocs.notificationservice.application.port.input.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.notification.NotificationNotFoundException;

@Service
@RequiredArgsConstructor
public class MarkAsReadUseCaseImpl implements MarkAsReadUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public void execute(MarkAsReadInputModel inputModel) {
        var recipientOptional = repository.findByRecipientIdAndNotificationId(inputModel.recipientId(), inputModel.notificationId());
        if (recipientOptional.isEmpty())
            throw new NotificationNotFoundException(inputModel.notificationId());
        var recipient = recipientOptional.get();
        recipient.read();
        long modifierCol = repository.markAsRead(recipient.getRecipientId(), recipient.getNotificationId());
        if (modifierCol <= 0)
            throw new UpdateFailedException();
    }
}
