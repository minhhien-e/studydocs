package studydocs.notificationservice.domain.factory.abstracts;

import studydocs.notificationservice.domain.model.entity.Recipient;

import java.util.UUID;

public interface RecipientFactory {
    Recipient create(UUID recipientId, UUID notificationId);
}
