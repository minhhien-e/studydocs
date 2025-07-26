package studydocs.notificationservice.shared.exception.concrete.valueobjects.notification.channel;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingNotificationChanelFieldException extends RequiredFieldMissingException {
    private static final String format = "Kênh thông báo của %s";
    public MissingNotificationChanelFieldException(String domainName) {
        super(String.format(format, domainName));
    }
}
