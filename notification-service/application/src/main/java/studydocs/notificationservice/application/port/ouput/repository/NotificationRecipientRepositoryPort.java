package studydocs.notificationservice.application.port.ouput.repository;

import studydocs.notificationservice.application.port.input.dto.paging.SliceOutput;
import studydocs.notificationservice.domain.entities.NotificationRecipient;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRecipientRepositoryPort {
    SliceOutput<NotificationRecipient> findByRecipientId(UUID recipientId, LocalDateTime createdAt,  int limit);
    void save(NotificationRecipient notificationRecipient);

    boolean hasAnyUnread(UUID uuid);
    int countUnread(UUID recipientId);

    long markAllAsRead(UUID recipientId);

    long markAsRead(UUID recipientId, UUID notificationId);

    Optional<NotificationRecipient> findByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId);
}
