package studydocs.notification.application.port.out.repository;

import studydocs.notification.application.dto.projection.NotificationProjection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface NotificationRepository {
    Integer countUnread(UUID recipientId);
    List<NotificationProjection> getByRecipientId(UUID recipientId, boolean deleted, LocalDateTime createdAt, int limit);
    List<UUID> getUnreadNotificationIdsByRecipientId(UUID recipientId, int batchSize, LocalDateTime lastSeenCreatedAt);
    List<UUID> getDeletedNotificationIdsByRecipientId(UUID recipientId, int batchSize, LocalDateTime lastSeenCreatedAt);
}
