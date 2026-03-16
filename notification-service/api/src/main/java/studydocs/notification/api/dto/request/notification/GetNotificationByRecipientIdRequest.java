package studydocs.notification.api.dto.request.notification;

import java.time.LocalDateTime;

public record GetNotificationByRecipientIdRequest(Boolean isDeleted, LocalDateTime nextCursor, Integer limit) {
}
