package studydocs.notification.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studydocs.notification.domain.policy.NotificationAccessPolicy;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.policy.UniqueNotificationTemplatePolicy;
import studydocs.notification.domain.repository.NotificationTemplateRepository;
import studydocs.notification.domain.repository.UserNotificationProfileRepository;
import studydocs.notification.domain.service.NotificationAccessPolicyImpl;
import studydocs.notification.domain.service.NotificationSendPolicyImpl;
import studydocs.notification.domain.service.UniqueNotificationTemplatePolicyImpl;

@Configuration
public class DomainPolicyConfig {
    @Bean
    public NotificationAccessPolicy notificationAccessPolicy() {
        return new NotificationAccessPolicyImpl();
    }
    @Bean
    public NotificationSendPolicy notificationSendPolicy(UserNotificationProfileRepository userRepository, NotificationTemplateRepository notificationTemplateRepository) {
        return new NotificationSendPolicyImpl(userRepository, notificationTemplateRepository);
    }
    @Bean
    public UniqueNotificationTemplatePolicy uniqueNotificationTemplatePolicy(NotificationTemplateRepository notificationTemplateRepository) {
        return new UniqueNotificationTemplatePolicyImpl(notificationTemplateRepository);
    }
}
