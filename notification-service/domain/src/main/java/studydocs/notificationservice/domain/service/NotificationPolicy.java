package studydocs.notificationservice.domain.service;

import studydocs.notificationservice.domain.model.entity.Recipient;

import java.time.LocalDateTime;

public class NotificationPolicy {
    public static boolean isTrashExpired(Recipient recipient) {
        if (recipient.getDeletionTime() == null) return false;
        return recipient.getDeletionTime().getValue().plusDays(30).isBefore(LocalDateTime.now());
    }
}
