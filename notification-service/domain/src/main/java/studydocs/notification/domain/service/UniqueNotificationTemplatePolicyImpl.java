package studydocs.notification.domain.service;

import studydocs.notification.domain.exception.template.NotificationTemplateAlreadyExistsException;
import studydocs.notification.domain.policy.UniqueNotificationTemplatePolicy;
import studydocs.notification.domain.repository.NotificationTemplateRepository;

public class UniqueNotificationTemplatePolicyImpl implements UniqueNotificationTemplatePolicy {
    private final NotificationTemplateRepository notificationTemplateRepository;
    public UniqueNotificationTemplatePolicyImpl(NotificationTemplateRepository notificationTemplateRepository) {
        this.notificationTemplateRepository = notificationTemplateRepository;
    }
    @Override
    public void checkNameUnique(String name) {
        if(notificationTemplateRepository.existsByName(name)){
            throw new NotificationTemplateAlreadyExistsException(name);
        }
    }
}
