package studydocs.notification.infrastructure.adapter.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.payload.UserDataProvidePayload;
import studydocs.notification.application.enums.NotificationDataProviderPrefix;
import studydocs.notification.application.port.out.provider.NotificationDataProvider;
import studydocs.notification.application.port.out.repository.UserQueries;
import studydocs.notification.infrastructure.adapter.provider.helper.DataProviderBuilder;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserNotificationDataProvider implements NotificationDataProvider<UserDataProvidePayload> {
    private final UserQueries userQueries;
    private final DataProviderBuilder dataProviderBuilder;

    @Override
    public String getSupportPrefix() {
        return NotificationDataProviderPrefix.USER.getPrefix();
    }

    @Override
    public String getGroupName() {
        return "Thông tin người nhận";
    }

    @Override
    public Map<String, Object> getData(UserDataProvidePayload payload) {
        var user = userQueries.getById(payload.recipientId());
         return dataProviderBuilder.buildDataProvider(user, getSupportPrefix());
    }

    @Override
    public Map<String, String> getAvailableMetadata() {
        return Map.of(
                "$user.name", "Tên người nhận",
                "$user.id", "ID người nhận"
        );
    }
}
