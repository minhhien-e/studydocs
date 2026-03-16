package studydocs.notification.infrastructure.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studydocs.notification.application.port.out.messaging.NotificationSenderPort;
import studydocs.notification.domain.enums.NotificationChannel;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotificationSenderConfig {
    @Bean
    public Map<String, NotificationSenderPort> notificationSenderMap(@Qualifier("emailNotificationSenderAdapter") NotificationSenderPort emailSender,
                                                                     @Qualifier("fcmNotificationSenderAdapter") NotificationSenderPort fcmSender) {
        var notificationSenderMap = new HashMap<String, NotificationSenderPort>();
        notificationSenderMap.put(NotificationChannel.EMAIL.name(), emailSender);
        notificationSenderMap.put(NotificationChannel.PUSH.name(), fcmSender);
        return notificationSenderMap;
    }

}
