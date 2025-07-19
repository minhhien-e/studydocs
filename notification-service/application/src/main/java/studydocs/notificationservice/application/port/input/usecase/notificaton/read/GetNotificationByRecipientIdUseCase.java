package studydocs.notificationservice.application.port.input.usecase.notificaton.read;

import studydocs.notificationservice.application.port.input.dto.inputmodel.notification.read.GetNotificationByRecipientIdInputModel;
import studydocs.notificationservice.application.port.input.dto.outputmodel.notification.NotificationOutputModel;
import studydocs.notificationservice.application.port.input.dto.paging.SliceInput;
import studydocs.notificationservice.application.port.input.dto.paging.SliceOutput;

public interface GetNotificationByRecipientIdUseCase {
    SliceOutput<NotificationOutputModel> execute(SliceInput<GetNotificationByRecipientIdInputModel> inputModel);
}
