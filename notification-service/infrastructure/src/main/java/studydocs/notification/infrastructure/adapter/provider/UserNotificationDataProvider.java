package studydocs.notification.infrastructure.adapter.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.port.in.provider.NotificationDataProvider;
import studydocs.notification.application.port.out.remote.RemoteUserServicePort;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserNotificationDataProvider implements NotificationDataProvider {
    private final RemoteUserServicePort remoteUserServicePort;
    private final ObjectMapper objectMapper;

    @Override
    public String getSupportPrefix() {
        return "user";
    }

    @Override
    public String getGroupName() {
        return "Thông tin người nhận";
    }

    @Override
    public Map<String, Object> getData(UUID recipientId) {
        var user = remoteUserServicePort.getById(recipientId);
        if (user == null) {
            return Map.of();
        }

        Map<String, Object> userData = objectMapper.convertValue(user, new TypeReference<>() {
        });

        return userData.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> getSupportPrefix() + "." + e.getKey(),
                        Map.Entry::getValue
                ));
    }

    @Override
    public Map<String, String> getAvailableMetadata() {
        return Map.of(
                "user.name", "Tên người nhận",
                "user.id", "ID người nhận"
        );
    }
}
