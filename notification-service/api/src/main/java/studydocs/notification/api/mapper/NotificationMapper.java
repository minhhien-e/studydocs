package studydocs.notification.api.mapper;

import studydocs.notification.api.dto.request.notification.*;
import studydocs.notification.application.dto.command.notification.*;
import studydocs.notification.application.dto.query.notification.CountUnreadQuery;
import studydocs.notification.application.dto.query.notification.GetNotificationByRecipientIdQuery;

import java.time.LocalDateTime;
import java.util.UUID;

public final class NotificationMapper {
    /// Create
    public static AddNotificationCommand toCommand(UUID userId, AddNotificationRequest request) {
        return new AddNotificationCommand(userId, request.templateId(), request.channel(), request.category(), request.templateData(), request.personalizedData());
    }

    public static ReceiveNotificationCommand toCommand(UUID userId, ReceiveNotificationRequest request) {
        return new ReceiveNotificationCommand(userId, request.getNotificationId(), request.getPersonalizedData());
    }

    /// Read
    public static GetNotificationByRecipientIdQuery toQuery(UUID userId, GetNotificationByRecipientIdRequest request) {
        LocalDateTime receivedAt = request.receivedAt() == null ? LocalDateTime.now() : request.receivedAt();
        return new GetNotificationByRecipientIdQuery(userId, request.isDeleted(), receivedAt, request.limit());
    }

    public static CountUnreadQuery toQuery(UUID userId, CountUnreadNotificationRequest request) {
        return new CountUnreadQuery(userId);
    }

    /// Update
    public static MarkAllAsReadCommand toCommand(UUID userId, MarkAllAsReadRequest request) {
        return new MarkAllAsReadCommand(userId);
    }

    public static MarkAsReadCommand toCommand(UUID userId, MarkAsReadRequest request) {
        return new MarkAsReadCommand(request.notificationId(), userId);
    }

    public static RestoreNotificationsCommand toCommand(UUID userId, RestoreNotificationsRequest request) {
        return new RestoreNotificationsCommand(request.notificationIds(), userId);
    }

    /// Delete
    public static SoftDeleteNotificationCommand toCommand(UUID userId, SoftDeleteNotificationRequest request) {
        return new SoftDeleteNotificationCommand(request.notificationId(), userId);
    }

    public static HardDeleteNotificationCommand toCommand(UUID userId, HardDeleteNotificationRequest request) {
        return new HardDeleteNotificationCommand(request.notificationId(), userId);
    }
}
