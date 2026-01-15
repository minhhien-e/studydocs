package studydocs.notification.application.dto.command.userprofile;

import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record UpdateEmailCommand(
        UUID userId,
        String newEmail
) implements Request<Void> {
}
