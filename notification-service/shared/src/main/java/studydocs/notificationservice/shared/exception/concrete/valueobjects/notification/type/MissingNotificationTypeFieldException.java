package studydocs.notificationservice.shared.exception.concrete.valueobjects.notification.type;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingNotificationTypeFieldException extends RequiredFieldMissingException {
    public MissingNotificationTypeFieldException() {
        super("Loại thông báo");
    }
}
