package studydocs.notification.application.port.in.provider;

import java.util.Map;
import java.util.UUID;

public interface NotificationDataProvider {
    String getSupportPrefix();

    String getGroupName();

    Map<String, Object> getData(UUID recipientId);

    Map<String, String> getAvailableMetadata();

    default boolean isNeeded(String content) {
        return content != null && content.contains("{" + getSupportPrefix() + ".");
    }
}
