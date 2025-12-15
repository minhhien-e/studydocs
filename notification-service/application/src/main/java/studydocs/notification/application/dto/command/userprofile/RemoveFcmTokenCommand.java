package studydocs.notification.application.dto.command.userprofile;

import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record RemoveFcmTokenCommand(
        UUID userId,
        String fcmToken
) implements Request<Void> {
}
