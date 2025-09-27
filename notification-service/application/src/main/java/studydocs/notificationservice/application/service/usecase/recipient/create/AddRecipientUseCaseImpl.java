package studydocs.notificationservice.application.service.usecase.recipient.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.recipient.create.ReceiveNotificationInput;
import studydocs.notificationservice.application.usecase.recipient.create.AddRecipientUseCase;
import studydocs.notificationservice.domain.model.aggregate.UserNotificationAggregate;
import studydocs.notificationservice.domain.repository.RecipientRepositoryPort;

@Service
@RequiredArgsConstructor
@Transactional
public class AddRecipientUseCaseImpl implements AddRecipientUseCase {
    private final RecipientRepositoryPort repository;

    @Override
    public void execute(ReceiveNotificationInput inputModel) {
        //Tạo dữ liệu
        var recipientId = inputModel.recipientId();
        var notificationId = inputModel.notificationId();
        var userNotificationAggregate = new UserNotificationAggregate(recipientId);
        //Xử lý logic
        var recipient = userNotificationAggregate.receiveNotification(notificationId);
        //Gọi repository
        repository.save(recipient);
    }
}
