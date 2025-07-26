package studydocs.notificationservice.shared.exception.concrete.valueobjects.notification.type;

import studydocs.notificationservice.shared.exception.abstracts.ResourceNotFoundException;

public class NotificationTypeNotFoundException extends ResourceNotFoundException {
    private static final String format = "Loại thông báo '%s'";

    public NotificationTypeNotFoundException(String type) {
        super(String.format(format, type));
    }
}
