package studydocs.notification.application.port.out.repository;

import studydocs.notification.application.dto.projection.NotificationRecipientProjection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface NotificationRecipientQueries {
    Integer countUnread(UUID recipientId);
    Long countByRecipientId(UUID recipientId, boolean deleted);
    List<NotificationRecipientProjection> getByRecipientId(UUID recipientId, boolean deleted, LocalDateTime lastSeenReceiveAt, int limit);
    List<UUID> getUnreadNotificationIdsByRecipientId(UUID recipientId, int batchSize, LocalDateTime lastSeenReceiveAt);
    List<UUID> getDeletedNotificationIdsByRecipientId(UUID recipientId, int batchSize, LocalDateTime lastSeenReceiveAt);
}
