package studydocs.notificationservice.application.usecase.recipient.delete;

import studydocs.notificationservice.application.dto.input.recipient.delete.HardDeleteNotificationInput;

public interface HardDeleteNotificationUseCase {
    void execute(HardDeleteNotificationInput inputModel);
}
