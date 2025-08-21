package studydocs.notificationservice.application.usecase.notificaton.delete;

import studydocs.notificationservice.application.dto.input.notification.delete.SoftDeleteNotificationInput;

public interface SoftDeleteNotificationUseCase {
    void execute(SoftDeleteNotificationInput inputModel);
}
