package studydocs.notificationservice.shared.exception.concrete.valueobjects.notification.channel;

import studydocs.notificationservice.shared.exception.abstracts.ResourceNotFoundException;

public class NotificationChannelNotFoundException extends ResourceNotFoundException {
    private static final String format = "Kênh thông báo '%s'";

    public NotificationChannelNotFoundException(String channel) {
        super(String.format(format, channel));
    }
}
