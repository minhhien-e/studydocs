package studydocs.notificationservice.application.usecase.notificaton.read;

import studydocs.notificationservice.application.dto.input.notification.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.application.dto.output.NotificationDto;
import studydocs.notificationservice.shared.paging.SliceInput;
import studydocs.notificationservice.shared.paging.SliceOutput;

public interface GetNotificationByRecipientIdUseCase {
    SliceOutput<NotificationDto> execute(SliceInput<GetNotificationByRecipientIdInput> inputModel);
}
