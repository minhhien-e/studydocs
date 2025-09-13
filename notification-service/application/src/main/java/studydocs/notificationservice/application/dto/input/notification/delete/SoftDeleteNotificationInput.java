package studydocs.notificationservice.application.dto.input.notification.delete;


import java.util.UUID;
public record SoftDeleteNotificationInput(UUID notificationId, UUID requesterId) {
}
