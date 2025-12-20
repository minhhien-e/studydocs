package studydocs.notification.application.dto.projection;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class NotificationRecipientProjection {
    private UUID id;
    private UUID recipientId;
    private String renderedSubject;
    private String renderedBody;
    private boolean isRead;
    private LocalDateTime receivedAt;
    private LocalDateTime deletedAt;
    private NotificationProjection notification;
}
