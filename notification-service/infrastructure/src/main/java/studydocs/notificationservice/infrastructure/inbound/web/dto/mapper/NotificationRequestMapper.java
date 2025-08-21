package studydocs.notificationservice.infrastructure.inbound.web.dto.mapper;

import studydocs.notificationservice.application.dto.input.notification.delete.HardDeleteNotificationInput;
import studydocs.notificationservice.application.dto.input.notification.delete.SoftDeleteNotificationInput;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.notification.delete.HardDeleteNotificationRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.notification.delete.SoftDeleteNotificationRequest;

import java.util.UUID;

public final class NotificationRequestMapper {
    //region Delete
    public static SoftDeleteNotificationInput toInput(SoftDeleteNotificationRequest request, UUID userId) {
        return new SoftDeleteNotificationInput(request.notificationId(), userId);
    }

    public static HardDeleteNotificationInput toInput(HardDeleteNotificationRequest request, UUID userId) {
        return new HardDeleteNotificationInput(request.notificationId(), userId);
    }
    //endregion
}
