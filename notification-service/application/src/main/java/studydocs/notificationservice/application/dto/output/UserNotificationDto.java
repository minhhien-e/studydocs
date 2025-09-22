package studydocs.notificationservice.application.dto.output;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record UserNotificationDto(UUID id, UUID senderId, UUID recipientId, UUID templateId,
                                  Map<String, Object> data, boolean isRead, LocalDateTime deletionTime,
                                  LocalDateTime creationAt) {
}
