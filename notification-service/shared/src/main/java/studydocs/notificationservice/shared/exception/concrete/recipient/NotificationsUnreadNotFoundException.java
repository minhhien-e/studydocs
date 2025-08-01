package studydocs.notificationservice.shared.exception.concrete.recipient;

import studydocs.notificationservice.shared.exception.abstracts.ResourceNotFoundException;

import java.util.UUID;

public class NotificationsUnreadNotFoundException extends ResourceNotFoundException {
    private static final String format = "Người dùng có Id '%s' không có thông báo nào chưa đọc";

    public NotificationsUnreadNotFoundException(UUID recipientId) {
        super(String.format(format, recipientId),true);
    }
}
