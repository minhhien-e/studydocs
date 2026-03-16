package studydocs.notification.application.dto.command.userprofile;

import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record UpdatePhoneNumberCommand(
        UUID userId,
        String newPhoneNumber
) implements Request<Void> {
}
