package studydocs.notification.application.dto.command.userprofile;

import studydocs.notification.application.dto.base.Request;

public record RemoveFcmTokenCommand(
        String fcmToken
) implements Request<Void> {
}
