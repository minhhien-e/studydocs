package studydocs.notification.application.dto.command.userprofile;

import studydocs.notification.application.dto.base.Command;

import java.util.UUID;

public record UpdateNotificationPreferencesCommand(
        UUID userId,
        boolean pushEnabled,
        boolean emailEnabled,
        boolean smsEnabled
) implements Command<Void> {
}
