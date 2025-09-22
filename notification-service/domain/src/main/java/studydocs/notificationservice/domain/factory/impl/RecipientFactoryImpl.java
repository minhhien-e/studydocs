package studydocs.notificationservice.domain.factory.impl;

import studydocs.notificationservice.domain.factory.abstracts.RecipientFactory;
import studydocs.notificationservice.domain.model.entity.Recipient;

import java.util.UUID;

public class RecipientFactoryImpl implements RecipientFactory {
    @Override
    public Recipient create(UUID recipientId, UUID notificationId) {
        return new Recipient(recipientId, notificationId);
    }
}
