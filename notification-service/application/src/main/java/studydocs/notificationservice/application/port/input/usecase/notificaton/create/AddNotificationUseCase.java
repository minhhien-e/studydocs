package studydocs.notificationservice.application.port.input.usecase.notificaton.create;

import studydocs.notificationservice.application.port.input.dto.inputmodel.notification.create.AddNotificationInputModel;

public interface AddNotificationUseCase {
    void execute(AddNotificationInputModel inputModel);
}
