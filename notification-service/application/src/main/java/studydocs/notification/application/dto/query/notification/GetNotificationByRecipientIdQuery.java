package studydocs.notification.application.dto.query.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.CursorPaginationResult;
import studydocs.notification.application.dto.base.Request;
import studydocs.notification.application.dto.projection.NotificationRecipientProjection;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record GetNotificationByRecipientIdQuery(UUID recipientId,
                                                Boolean isDeleted,
                                                LocalDateTime receivedAt,
                                                Integer limit) implements Request<CursorPaginationResult<NotificationRecipientProjection>> {
    public GetNotificationByRecipientIdQuery {
        if (isDeleted == null) {
            isDeleted = false;
        }
        if (receivedAt == null) {
            receivedAt = LocalDateTime.now();
        }
        if (limit == null || limit <= 0) {
            limit = 10;
        }
    }
}
