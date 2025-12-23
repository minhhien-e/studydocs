package studydocs.notification.api.mapper;

import studydocs.notification.api.dto.request.notification.*;
import studydocs.notification.api.dto.view.NotificationMetadataView;
import studydocs.notification.api.dto.view.NotificationRecipientView;
import studydocs.notification.application.dto.command.notification.*;
import studydocs.notification.application.dto.projection.NotificationMetadataProjection;
import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.application.dto.query.notification.CountUnreadQuery;
import studydocs.notification.application.dto.query.notification.GetNotificationByRecipientIdQuery;
import studydocs.notification.application.dto.query.notification.GetNotificationMetadataQuery;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public final class NotificationMapper {
    /// Command
    public static AddNotificationCommand toCommand(UUID userId, AddNotificationRequest request) {
        var recipients = request.recipients().stream()
                .map(r -> RecipientData.builder()
                        .recipientId(r.recipientId())
                        .context(r.context())
                        .build())
                .toList();

        return AddNotificationCommand.builder()
                .senderId(userId)
                .templateId(request.templateId())
                .channel(request.channel())
                .type(request.type())
                .subjectData(request.snapshotSubjectData())
                .bodyData(request.snapshotBodyData())
                .recipients(recipients)
                .build();
    }

    public static ReceiveNotificationCommand toCommand(UUID userId, ReceiveNotificationRequest request) {
        return ReceiveNotificationCommand.builder()
                .recipientData(RecipientData.builder()
                        .recipientId(userId)
                        .context(request.getContext())
                        .build())
                .notificationId(request.getNotificationId())
                .build();
    }

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

    /// Query
    public static GetNotificationByRecipientIdQuery toQuery(UUID userId, GetNotificationByRecipientIdRequest request) {
        LocalDateTime receivedAt = request.nextCursor() == null ? LocalDateTime.now() : request.nextCursor();
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

    public static GetNotificationMetadataQuery toQuery(GetNotificationMetadataRequest request) {
        return new GetNotificationMetadataQuery();
    }

    /// View
    public static NotificationRecipientView toView(NotificationRecipientProjection projection) {
        return new NotificationRecipientView(
                projection.getNotification().getId(),
                projection.getNotification().getSenderName(),
                projection.getRenderedSubject(),
                projection.getRenderedBody(),
                projection.getNotification().getType(),
                projection.isRead(),
                projection.getReceivedAt(),
                projection.getDeletedAt()
        );
    }
    public static NotificationMetadataView toView(NotificationMetadataProjection projection){
        return new NotificationMetadataView(projection.groupName(),projection.items());
    }
}
