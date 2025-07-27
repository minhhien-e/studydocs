package studydocs.notificationservice.shared.exception.concrete.recipient;

import studydocs.notificationservice.shared.exception.abstracts.ResourceNotFoundException;

import java.util.UUID;

public class NotificationUnreadNotFoundException extends ResourceNotFoundException {
    private static final String format = "Thông báo có Id '%s' đã đọc rồi";

    public NotificationUnreadNotFoundException(UUID notificationId) {
        super(String.format(format, notificationId),true);
    }
}
