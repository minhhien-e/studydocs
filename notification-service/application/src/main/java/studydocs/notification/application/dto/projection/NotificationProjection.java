package studydocs.notification.application.dto.projection;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Builder
@Data
public class NotificationProjection {
    private UUID id;
    private UUID templateId;
    private UUID senderId;
    private String senderName;
    private String channel;
    private String type;
    private String snapshotSubject;
    private String snapshotBody;
    private LocalDateTime createdAt;
}