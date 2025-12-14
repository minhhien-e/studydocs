package studydocs.notification.application.dto.command.userprofile;

import studydocs.notification.application.dto.base.Command;

import java.util.UUID;

public record RegisterFcmTokenCommand(
        UUID userId,
        String fcmToken
) implements Command<Void> {
}
