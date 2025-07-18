package studydocs.notificationservice.shared.exception.concrete.notification.validation;

import studydocs.notificationservice.shared.exception.validation.InvalidValueException;

public class InvalidNotificationTypeException extends InvalidValueException {
    public InvalidNotificationTypeException(String channel) {
        super("Loại thông báo '" + channel + "'");
    }
}
