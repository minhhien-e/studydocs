package studydocs.notificationservice.shared.exception.infrastructure;

import studydocs.notificationservice.shared.enums.InfrastructureErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.InfrastructureException;

public class EmailSendFailureException extends InfrastructureException {
    private final static String message = "Gửi mail không thành công";

    public EmailSendFailureException() {
        super(message, InfrastructureErrorCode.EMAIL_SEND_FAILURE);
    }
}
