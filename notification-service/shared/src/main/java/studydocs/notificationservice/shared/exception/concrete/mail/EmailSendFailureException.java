package studydocs.notificationservice.shared.exception.concrete.mail;

public class EmailSendFailureException extends RuntimeException {
    public EmailSendFailureException(String message) {
        super("Gửi mail thất bại với lỗi: " + message);
    }
}
