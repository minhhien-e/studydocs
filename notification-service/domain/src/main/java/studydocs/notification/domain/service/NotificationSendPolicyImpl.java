package studydocs.notification.domain.service;

import studydocs.notification.domain.exception.notification.SenderNotFoundException;
import studydocs.notification.domain.exception.recipient.NotificationRecipientAlreadyExistsException;
import studydocs.notification.domain.exception.recipient.RecipientNotFoundException;
import studydocs.notification.domain.exception.recipient.RecipientsNotFoundException;
import studydocs.notification.domain.exception.template.NotificationTemplateNotFoundException;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;
import studydocs.notification.domain.repository.NotificationTemplateRepository;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;

import java.util.List;
import java.util.UUID;

public class NotificationSendPolicyImpl implements NotificationSendPolicy {
    private final UserNotificationProfileRepository userRepository;
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationRecipientRepository notificationRecipientRepository;
    public NotificationSendPolicyImpl(UserNotificationProfileRepository userRepository, NotificationTemplateRepository notificationTemplateRepository, NotificationRecipientRepository notificationRecipientRepository) {
        this.userRepository = userRepository;
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.notificationRecipientRepository = notificationRecipientRepository;
    }

    @Override
    public void ensureCanCreate(UUID senderId, UUID templateId) {
        if (!userRepository.existsByUserId(senderId)) {
            throw new SenderNotFoundException(senderId);
        }
        if (!notificationTemplateRepository.existsById(templateId)) {
            throw new NotificationTemplateNotFoundException(templateId);
        }

    }

    @Override
    public void ensureCanReceive(UUID notificationId, UUID recipientId) {
        ensureCanSend(recipientId);
        if(!notificationRecipientRepository.existsByNotificationIdAndRecipientId(notificationId, recipientId)){
            throw new NotificationRecipientAlreadyExistsException(notificationId, recipientId);
        }
    }

    @Override
    public void ensureCanSend(UUID recipientId) {
        if (!userRepository.existsByUserId(recipientId)) {
            throw new RecipientNotFoundException(recipientId);
        }
    }
}
