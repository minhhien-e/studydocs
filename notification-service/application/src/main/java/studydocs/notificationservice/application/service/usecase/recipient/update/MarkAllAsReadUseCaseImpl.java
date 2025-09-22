package studydocs.notificationservice.application.service.usecase.recipient.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.domain.repository.RecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class MarkAllAsReadUseCaseImpl implements MarkAllAsReadUseCase {
    private final RecipientRepositoryPort repository;

    @Override
    public void execute(MarkAllAsReadInput inputModel) {
        //Load dữ liệu
        var recipientId = inputModel.recipientId();
        var userNotificationAggregate = repository.findByRecipientId(recipientId);
        //Xử lý logic
        userNotificationAggregate.markAllNotificationsAsRead();
        //Gọi repository
        repository.markAllAsRead(recipientId);
    }
}
