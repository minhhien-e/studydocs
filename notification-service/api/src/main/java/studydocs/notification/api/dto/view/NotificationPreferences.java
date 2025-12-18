package studydocs.notification.api.dto.view;

public record NotificationPreferences(
        boolean pushEnabled,
        boolean emailEnabled,
        boolean smsEnabled
) {
}
