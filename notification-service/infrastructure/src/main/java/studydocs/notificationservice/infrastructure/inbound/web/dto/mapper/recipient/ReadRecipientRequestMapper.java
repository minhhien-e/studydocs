package studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.recipient;

import studydocs.notificationservice.application.dto.input.notification.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.application.dto.input.recipient.create.CountUnreadInput;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read.CountUnreadRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read.GetNotificationByRecipientIdRequest;
import studydocs.notificationservice.shared.paging.SliceInput;

import java.time.LocalDateTime;

public class ReadRecipientRequestMapper {
    //region Count Unread
    public static CountUnreadInput toInput(CountUnreadRequest request) {
        return new CountUnreadInput(request.recipientId());
    }
    //endregion
    //region Get By Recipient Id
    public static SliceInput<GetNotificationByRecipientIdInput> toInput(GetNotificationByRecipientIdRequest request) {
        var limit = request.limit() <= 0 ? 10 : request.limit();
        var input = new GetNotificationByRecipientIdInput(request.recipientId(),
                request.createdAt().orElse(LocalDateTime.now()));
        return new SliceInput<>(input, limit);
    }
    //endregion
}
