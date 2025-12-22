package studydocs.notification.infrastructure.adapter.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.payload.UserDataProvidePayload;
import studydocs.notification.application.port.out.provider.NotificationDataProvider;
import studydocs.notification.application.port.out.remote.RemoteUserServicePort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserNotificationDataProvider implements NotificationDataProvider<UserDataProvidePayload> {
    private final RemoteUserServicePort remoteUserServicePort;
    private final ObjectMapper objectMapper;

    @Override
    public String getSupportPrefix() {
        return "$user";
    }

    @Override
    public String getGroupName() {
        return "Thông tin người nhận";
    }

    @Override
    public Map<String, Object> getData(UserDataProvidePayload payload) {
        var user = remoteUserServicePort.getById(payload.recipientId());
        if (user == null) {
            return Map.of();
        }

        Map<String, Object> userData = objectMapper.convertValue(user, new TypeReference<>() {
        });

        return userData.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(
                        e -> getSupportPrefix() + "." + e.getKey(),
                        Map.Entry::getValue
                ));
    }

    @Override
    public Map<String, String> getAvailableMetadata() {
        return Map.of(
                "$user.name", "Tên người nhận",
                "$user.id", "ID người nhận"
        );
    }
}
