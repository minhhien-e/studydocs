package studydocs.notificationservice.application.usecase.notificaton.read;

import studydocs.notificationservice.application.dto.input.notification.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.application.dto.output.NotificationOutput;
import studydocs.notificationservice.shared.paging.SliceInput;
import studydocs.notificationservice.shared.paging.SliceOutput;

public interface GetNotificationByRecipientIdUseCase {
    SliceOutput<NotificationOutput> execute(SliceInput<GetNotificationByRecipientIdInput> inputModel);
}
