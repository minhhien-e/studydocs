package studydocs.notificationservice.shared.exception.concrete.notification;

public class MissingSenderIdInNotificationException extends RuntimeException {
    public MissingSenderIdInNotificationException() {
        super("Id người gửi");
    }
}
