package studydocs.notificationservice.infrastructure.inbound.web.dto.mapper;

import studydocs.notificationservice.application.dto.input.notification.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.application.dto.input.recipient.create.CountUnreadInput;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read.CountUnreadRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read.GetNotificationByRecipientIdRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update.MarkAllAsReadRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update.MarkAsReadRequest;
import studydocs.notificationservice.shared.paging.SliceInput;

import java.time.LocalDateTime;

public class RecipientRequestMapper {
    public static CountUnreadInput toInput(CountUnreadRequest request) {
        return new CountUnreadInput(request.recipientId());
    }

    public static SliceInput<GetNotificationByRecipientIdInput> toInput(GetNotificationByRecipientIdRequest request) {
        var limit = request.limit() <= 0 ? 10 : request.limit();
        var input = new GetNotificationByRecipientIdInput(request.recipientId(), request.createdAt().orElse(LocalDateTime.now()));
        return new SliceInput<>(input, limit);
    }

    public static MarkAllAsReadInput toInput(MarkAllAsReadRequest request) {
        return new MarkAllAsReadInput();
    }

    public static MarkAsReadInput toInput(MarkAsReadRequest request) {
        return new MarkAsReadInput(request.getNotificationId());
    }
}
