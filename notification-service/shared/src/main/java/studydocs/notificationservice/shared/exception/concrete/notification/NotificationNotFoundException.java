package studydocs.notificationservice.shared.exception.concrete.notification;

import studydocs.notificationservice.shared.exception.abstracts.ResourceNotFoundException;

import java.util.UUID;

public class NotificationNotFoundException extends ResourceNotFoundException {
    public NotificationNotFoundException(UUID id) {
        super("Thông báo với ID '" + id + "'");
    }
}
