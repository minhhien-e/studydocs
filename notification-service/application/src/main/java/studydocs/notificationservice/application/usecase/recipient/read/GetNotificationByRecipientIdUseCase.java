package studydocs.notificationservice.application.usecase.recipient.read;

import studydocs.notificationservice.application.dto.input.recipient.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.application.dto.output.NotificationDto;
import studydocs.notificationservice.shared.paging.SliceInput;
import studydocs.notificationservice.shared.paging.SliceOutput;

public interface GetNotificationByRecipientIdUseCase {
    SliceOutput<NotificationDto> execute(SliceInput<GetNotificationByRecipientIdInput> inputModel);
}
