package studydocs.notificationservice.application.usecase.recipient.update;

import studydocs.notificationservice.application.dto.input.recipient.update.RestoreNotificationsInput;

public interface RestoreNotificationsUseCase {
    void execute(RestoreNotificationsInput inputModel);
}
