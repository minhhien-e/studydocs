package studydocs.notification.application.dto.query.notification;

import lombok.Builder;
import studydocs.notification.application.dto.readmodel.NotificationReadModel;
import studydocs.notification.application.dto.base.Request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record GetNotificationByRecipientIdQuery(UUID recipientId,
                                                boolean isDeleted,
                                                LocalDateTime receivedAt,
                                                int limit) implements Request<List<NotificationReadModel>> {
}
