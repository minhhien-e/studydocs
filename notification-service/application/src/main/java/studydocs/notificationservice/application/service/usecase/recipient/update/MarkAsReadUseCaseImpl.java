package studydocs.notificationservice.application.service.usecase.recipient.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.domain.repository.RecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class MarkAsReadUseCaseImpl implements MarkAsReadUseCase {
    private final RecipientRepositoryPort repository;

    @Override
    public void execute(MarkAsReadInput inputModel) {
        //Load dữ liệu
        var recipientId = inputModel.recipientId();
        var notificationId = inputModel.notificationId();
        var userNotificationAggregate = repository.getByRecipientIdAndNotificationId(recipientId, notificationId);
        //Xử lý logic
        userNotificationAggregate.markNotificationAsRead(notificationId);
        //Gọi repository
        repository.markAsRead(recipientId, notificationId);
    }
}
