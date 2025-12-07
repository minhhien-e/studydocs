package studydocs.notification.api.dto.request.notification;

import java.time.LocalDateTime;

public record GetNotificationByRecipientIdRequest(boolean isDeleted, LocalDateTime receivedAt, int limit) {
}
