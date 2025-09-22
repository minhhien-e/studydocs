package studydocs.notificationservice.application.usecase.recipient.create;

import studydocs.notificationservice.application.dto.input.recipient.create.ReceiveNotificationInput;

public interface AddRecipientUseCase {
    void execute(ReceiveNotificationInput inputModel);
}
