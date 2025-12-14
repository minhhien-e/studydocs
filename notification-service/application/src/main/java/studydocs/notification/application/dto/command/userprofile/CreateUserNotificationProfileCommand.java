package studydocs.notification.application.dto.command.userprofile;

import studydocs.notification.application.dto.base.Command;

import java.util.UUID;

public record CreateUserNotificationProfileCommand(
        UUID userId,
        String email,
        String phoneNumber
) implements Command<Void> {
}
