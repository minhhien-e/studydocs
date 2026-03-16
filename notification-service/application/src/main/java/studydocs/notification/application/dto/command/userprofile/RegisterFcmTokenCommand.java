package studydocs.notification.application.dto.command.userprofile;

import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record RegisterFcmTokenCommand(
        UUID userId,
        String fcmToken
) implements Request<Void> {
}
