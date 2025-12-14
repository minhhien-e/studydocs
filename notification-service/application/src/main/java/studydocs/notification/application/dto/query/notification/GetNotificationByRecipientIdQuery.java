package studydocs.notification.application.dto.query.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.CursorPaginationResult;
import studydocs.notification.application.dto.base.Request;
import studydocs.notification.application.dto.projection.NotificationRecipientProjection;
import studydocs.notification.application.dto.view.NotificationRecipientView;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record GetNotificationByRecipientIdQuery(UUID recipientId,
                                                boolean isDeleted,
                                                LocalDateTime receivedAt,
                                                int limit) implements Request<CursorPaginationResult<NotificationRecipientProjection>> {
}
