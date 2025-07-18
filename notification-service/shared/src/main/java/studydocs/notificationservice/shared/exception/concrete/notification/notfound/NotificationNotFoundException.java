package studydocs.notificationservice.shared.exception.concrete.notification.notfound;

import studydocs.notificationservice.shared.exception.notfound.ResourceNotFoundException;

import java.util.UUID;

public class NotificationNotFoundException extends ResourceNotFoundException {
    public NotificationNotFoundException(UUID id) {
        super("Thông báo với ID '" + id + "'");
    }
}
