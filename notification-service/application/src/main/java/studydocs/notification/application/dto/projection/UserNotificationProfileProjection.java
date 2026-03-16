package studydocs.notification.application.dto.projection;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record UserNotificationProfileProjection(
        UUID id,
        UUID userId,
        List<String> fcmTokens,
        String emailAddress,
        String phoneNumber,
        boolean pushEnabled,
        boolean emailEnabled,
        boolean smsEnabled
) {
}
