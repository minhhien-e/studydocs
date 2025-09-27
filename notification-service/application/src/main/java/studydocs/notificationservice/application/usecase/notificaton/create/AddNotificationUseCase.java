package studydocs.notificationservice.application.usecase.notificaton.create;

import studydocs.notificationservice.application.dto.input.notification.create.AddNotificationInput;

import java.util.UUID;

public interface AddNotificationUseCase {
    UUID execute(AddNotificationInput inputModel);
}
