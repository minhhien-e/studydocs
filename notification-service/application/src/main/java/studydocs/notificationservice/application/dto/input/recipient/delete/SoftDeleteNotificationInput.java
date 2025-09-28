package studydocs.notificationservice.application.dto.input.recipient.delete;


import java.util.UUID;

public record SoftDeleteNotificationInput(UUID notificationId, UUID requesterId) {
}
