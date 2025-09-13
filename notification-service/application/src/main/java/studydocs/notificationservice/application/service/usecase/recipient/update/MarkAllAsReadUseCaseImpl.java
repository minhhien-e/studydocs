package studydocs.notificationservice.application.service.usecase.recipient.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.shared.exception.abstracts.UpdateFailedException;
import studydocs.notificationservice.shared.exception.concrete.recipient.NotificationsUnreadNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class MarkAllAsReadUseCaseImpl implements MarkAllAsReadUseCase {
    private final NotificationRecipientRepositoryPort repository;

    @Override
    public void execute(MarkAllAsReadInput inputModel) {
        if (!repository.hasAnyUnread(inputModel.getRecipientId()))
            throw new NotificationsUnreadNotFoundException(inputModel.getRecipientId());
        long modifierCol = repository.markAllAsRead(inputModel.getRecipientId());
        if (modifierCol <= 0)
            throw new UpdateFailedException();
    }
}
