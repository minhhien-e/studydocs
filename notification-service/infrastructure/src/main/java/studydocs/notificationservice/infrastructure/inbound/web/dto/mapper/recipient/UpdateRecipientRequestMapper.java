package studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.recipient;

import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update.MarkAllAsReadRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update.MarkAsReadRequest;

public class UpdateRecipientRequestMapper {
    //region Mark All As Read
    public static MarkAllAsReadInput toInput(MarkAllAsReadRequest request) {
        return new MarkAllAsReadInput();
    }

    //endregion
    //region Mark as Read
    public static MarkAsReadInput toInput(MarkAsReadRequest request) {
        return new MarkAsReadInput(request.getNotificationId());
    }

    //endregion
}
