package studydocs.notificationservice.application.port.input.dto.outputmodel.notification;

import lombok.Builder;
import studydocs.notificationservice.shared.enums.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
public record NotificationOutputModel(UUID id, UUID senderId, UUID recipientId,
                                      String subject, String content,
                                      boolean isRead,
                                      NotificationType type,
                                      LocalDateTime createdAt ) {
}
