package studydocs.notificationservice.domain.service;

import studydocs.notificationservice.domain.model.entity.NotificationRecipient;

import java.time.LocalDateTime;

public class NotificationCleanerPolicy {
    public static boolean isExpired(NotificationRecipient recipient) {
        return recipient.getDeletionTime().plusDays(30).isBefore(LocalDateTime.now());
    }
}
