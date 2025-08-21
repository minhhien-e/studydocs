package studydocs.notificationservice.domain.repository;

import studydocs.notificationservice.domain.entity.NotificationRecipient;
import studydocs.notificationservice.shared.paging.SliceOutput;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface NotificationRecipientRepositoryPort {
    SliceOutput<NotificationRecipient> findByRecipientId(UUID recipientId, LocalDateTime createdAt, int limit);

    void save(NotificationRecipient notificationRecipient);

    void deleteById(UUID id);

    boolean hasAnyUnread(UUID uuid);

    int countUnread(UUID recipientId);

    long markAllAsRead(UUID recipientId);

    long markAsRead(UUID recipientId, UUID notificationId);

    NotificationRecipient getByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId);

    void updateDeletedAt(NotificationRecipient recipient);

    List<NotificationRecipient> findAll();
}
