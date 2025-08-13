package studydocs.notificationservice.application.usecase.notificaton.create;

import studydocs.notificationservice.application.dto.input.notification.create.AddNotificationInput;

public interface AddNotificationUseCase {
    void execute(AddNotificationInput inputModel);
}
