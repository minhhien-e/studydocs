package studydocs.notification.application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import studydocs.notification.application.dto.payload.UserDataProvidePayload;
import studydocs.notification.application.dto.payload.base.DataProvidePayload;
import studydocs.notification.application.port.out.provider.NotificationDataProvider;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class NotificationDataProviderConfig {
    private final NotificationDataProvider<UserDataProvidePayload> userNotificationDataProvider;

    @Bean
    public Map<Class<? extends DataProvidePayload>
            , NotificationDataProvider<? extends DataProvidePayload>> notificationDataProviderMap() {
        Map<Class<? extends DataProvidePayload>, NotificationDataProvider<? extends DataProvidePayload>> map = new HashMap<>();
        map.put(UserDataProvidePayload.class, userNotificationDataProvider);
        return map;
    }
}
