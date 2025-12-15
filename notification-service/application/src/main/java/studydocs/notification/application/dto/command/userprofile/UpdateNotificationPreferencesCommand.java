package studydocs.notification.application.dto.command.userprofile;

import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record UpdateNotificationPreferencesCommand(
        UUID userId,
        boolean pushEnabled,
        boolean emailEnabled,
        boolean smsEnabled
) implements Request<Void> {
}
