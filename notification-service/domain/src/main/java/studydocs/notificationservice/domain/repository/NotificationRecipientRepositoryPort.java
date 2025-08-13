package studydocs.notificationservice.domain.repository;

import studydocs.notificationservice.domain.entity.NotificationRecipient;
import studydocs.notificationservice.shared.paging.SliceOutput;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRecipientRepositoryPort {
    SliceOutput<NotificationRecipient> findByRecipientId(UUID recipientId, LocalDateTime createdAt, int limit);
    void save(NotificationRecipient notificationRecipient);

    boolean hasAnyUnread(UUID uuid);
    int countUnread(UUID recipientId);

    long markAllAsRead(UUID recipientId);

    long markAsRead(UUID recipientId, UUID notificationId);

    Optional<NotificationRecipient> findByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId);
}
