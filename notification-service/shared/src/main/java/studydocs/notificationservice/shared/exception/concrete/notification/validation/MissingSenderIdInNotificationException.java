package studydocs.notificationservice.shared.exception.concrete.notification.validation;

public class MissingSenderIdInNotificationException extends RuntimeException {
    public MissingSenderIdInNotificationException() {
        super("Id người gửi");
    }
}
