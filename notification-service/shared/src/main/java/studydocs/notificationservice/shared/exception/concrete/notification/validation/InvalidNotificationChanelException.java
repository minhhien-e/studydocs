package studydocs.notificationservice.shared.exception.concrete.notification.validation;

import studydocs.notificationservice.shared.exception.validation.InvalidValueException;

public class InvalidNotificationChanelException extends InvalidValueException {
    public InvalidNotificationChanelException(String channel) {
        super("kênh '" + channel + "'");
    }
}
