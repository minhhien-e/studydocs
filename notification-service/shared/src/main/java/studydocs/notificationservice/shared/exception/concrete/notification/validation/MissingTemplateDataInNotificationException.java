package studydocs.notificationservice.shared.exception.concrete.notification.validation;

public class MissingTemplateDataInNotificationException extends RuntimeException {
    public MissingTemplateDataInNotificationException() {
        super("Nội dung thông báo");
    }
}
