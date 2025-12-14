package studydocs.notification.application.dto.view;

public record NotificationPreferences(
        boolean pushEnabled,
        boolean emailEnabled,
        boolean smsEnabled
) {
}
