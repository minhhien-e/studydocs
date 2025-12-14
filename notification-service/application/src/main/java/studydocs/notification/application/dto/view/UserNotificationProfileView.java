package studydocs.notification.application.dto.view;

import java.util.List;
import java.util.UUID;

public record UserNotificationProfileView(
        UUID id,
        List<String> fcmTokens,
        String emailAddress,
        String phoneNumber,
        NotificationPreferences preferences
) {
}
