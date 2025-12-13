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
        var recipients = request.recipients().stream()
                .map(r -> studydocs.notification.application.dto.command.notification.RecipientData.builder()
                        .recipientId(r.recipientId())
                        .subjectData(r.subjectData())
                        .bodyData(r.bodyData())
                        .build())
                .toList();
        
        return AddNotificationCommand.builder()
                .senderId(userId)
                .templateId(request.templateId())
                .channel(request.channel())
                .category(request.category())
                .snapshotSubjectData(request.snapshotSubjectData())
                .snapshotBodyData(request.snapshotBodyData())
                .recipients(recipients)
                .build();
    }

    public static ReceiveNotificationCommand toCommand(UUID userId, ReceiveNotificationRequest request) {
        return ReceiveNotificationCommand.builder()
                .recipientId(userId)
                .notificationId(request.getNotificationId())
                .subjectData(request.getSubjectData())
                .bodyData(request.getBodyData())
                .build();
    }

    /// Read
    public static GetNotificationByRecipientIdQuery toQuery(UUID userId, GetNotificationByRecipientIdRequest request) {
        LocalDateTime receivedAt = request.receivedAt() == null ? LocalDateTime.now() : request.receivedAt();
        return GetNotificationByRecipientIdQuery.builder()
                .recipientId(userId)
                .isDeleted(request.isDeleted())
                .receivedAt(receivedAt)
                .limit(request.limit())
                .build();
    }

    public static CountUnreadQuery toQuery(UUID userId, CountUnreadNotificationRequest request) {
        return CountUnreadQuery.builder()
                .recipientId(userId)
                .build();
    }

    /// Update
    public static MarkAllAsReadCommand toCommand(UUID userId, MarkAllAsReadRequest request) {
        return MarkAllAsReadCommand.builder()
                .recipientId(userId)
                .build();
    }

    public static MarkAsReadCommand toCommand(UUID userId, MarkAsReadRequest request) {
        return MarkAsReadCommand.builder()
                .notificationId(request.notificationId())
                .recipientId(userId)
                .build();
    }

    public static RestoreNotificationsCommand toCommand(UUID userId, RestoreNotificationsRequest request) {
        return RestoreNotificationsCommand.builder()
                .notificationIds(request.notificationIds())
                .recipientId(userId)
                .build();
    }

    /// Delete
    public static SoftDeleteNotificationCommand toCommand(UUID userId, SoftDeleteNotificationRequest request) {
        return SoftDeleteNotificationCommand.builder()
                .notificationId(request.notificationId())
                .requesterId(userId)
                .build();
    }

    public static HardDeleteNotificationCommand toCommand(UUID userId, HardDeleteNotificationRequest request) {
        return HardDeleteNotificationCommand.builder()
                .notificationId(request.notificationId())
                .requesterId(userId)
                .build();
    }
}
