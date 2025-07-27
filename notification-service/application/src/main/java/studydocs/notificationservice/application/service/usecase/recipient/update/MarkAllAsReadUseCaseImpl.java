package studydocs.notificationservice.application.service.usecase.recipient.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.update.MarkAllAsReadInputModel;
import studydocs.notificationservice.application.port.input.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationsUnreadNotFoundException;

@Service
@RequiredArgsConstructor
public class MarkAllAsReadUseCaseImpl implements MarkAllAsReadUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public void execute(MarkAllAsReadInputModel inputModel) {
        if (!repository.hasAnyUnread(inputModel.recipientId()))
            throw new NotificationsUnreadNotFoundException(inputModel.recipientId());
        long modifierCol = repository.markAllAsRead(inputModel.recipientId());
        if (modifierCol <= 0)
            throw new UpdateFailedException();
    }
}
