package studydocs.notification.domain.service;

import studydocs.notification.domain.exception.template.NotificationTemplateNotFoundException;
import studydocs.notification.domain.exception.recipient.RecipientsNotFoundException;
import studydocs.notification.domain.exception.notification.SenderNotFoundException;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationTemplateRepository;
import studydocs.notification.domain.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class NotificationSendPolicyImpl implements NotificationSendPolicy {
    private final UserRepository userRepository;
    private final NotificationTemplateRepository notificationTemplateRepository;

    public NotificationSendPolicyImpl(UserRepository userRepository, NotificationTemplateRepository notificationTemplateRepository) {
        this.userRepository = userRepository;
        this.notificationTemplateRepository = notificationTemplateRepository;
    }

    @Override
    public void ensureCanCreate(UUID senderId, UUID templateId) {
        if (!userRepository.existsById(senderId)) {
            throw new SenderNotFoundException(senderId);
        }
        if (!notificationTemplateRepository.existsById(templateId)) {
            throw new NotificationTemplateNotFoundException(templateId);
        }

    }

    @Override
    public void ensureCanSend(List<UUID> recipientIds) {
        if (!userRepository.existsAllByIdIn(recipientIds)) {
            throw new RecipientsNotFoundException(recipientIds);
        }
    }
}
