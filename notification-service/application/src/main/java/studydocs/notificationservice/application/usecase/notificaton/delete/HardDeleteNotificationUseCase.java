package studydocs.notificationservice.application.usecase.notificaton.delete;

import studydocs.notificationservice.application.dto.input.notification.delete.HardDeleteNotificationInput;

public interface HardDeleteNotificationUseCase {
    void execute(HardDeleteNotificationInput inputModel);
}
