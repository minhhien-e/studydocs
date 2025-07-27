package studydocs.notificationservice.application.service.usecase.recipient.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAsReadInputModel;
import studydocs.notificationservice.application.port.input.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.notification.NotificationNotFoundException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationUnreadNotFoundException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationsUnreadNotFoundException;

@Service
@RequiredArgsConstructor
public class MarkAsReadUseCaseImpl implements MarkAsReadUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public void execute(MarkAsReadInputModel inputModel) {
        if (!repository.existsByRecipientIdAndNotificationId(inputModel.recipientId(), inputModel.notificationId()))
            throw new NotificationNotFoundException(inputModel.notificationId());
        if (!repository.isUnread(inputModel.recipientId(), inputModel.notificationId()))
            throw new NotificationUnreadNotFoundException(inputModel.notificationId());
        long modifierCol = repository.markAsRead(inputModel.recipientId(), inputModel.notificationId());
        if (modifierCol <= 0)
            throw new UpdateFailedException();
    }
}
