package studydocs.notificationservice.domain.repository;

import studydocs.notificationservice.domain.model.aggregate.UserNotificationAggregate;
import studydocs.notificationservice.domain.model.entity.Recipient;

import java.util.List;
import java.util.UUID;

public interface RecipientRepositoryPort {
    UserNotificationAggregate findByRecipientId(UUID recipientId);

    void save(Recipient notificationRecipient);

    void deleteById(UUID id);

    int countUnread(UUID recipientId);

    void markAllAsRead(UUID recipientId);

    void markAsRead(UUID recipientId, UUID notificationId);

    UserNotificationAggregate getByRecipientIdAndNotificationId(UUID recipientId, UUID notificationId);

    void updateDeletedAt(Recipient recipient);

    List<Recipient> findAll();

}
