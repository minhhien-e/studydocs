package studydocs.notificationservice.application.usecase.recipient.delete;

import studydocs.notificationservice.application.dto.input.recipient.delete.SoftDeleteNotificationInput;

public interface SoftDeleteNotificationUseCase {
    void execute(SoftDeleteNotificationInput inputModel);
}
