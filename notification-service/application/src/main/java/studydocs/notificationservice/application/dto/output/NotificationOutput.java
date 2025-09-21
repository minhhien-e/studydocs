package studydocs.notificationservice.application.dto.output;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record NotificationOutput(UUID id, UUID senderId, UUID recipientId,
                                 String subject, String content,
                                 boolean isRead,
                                 String type,
                                 LocalDateTime createdAt) {
}
