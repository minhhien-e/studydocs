package studydocs.notificationservice.domain.exceptions.vo.email;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class EmailContentTooLongException extends DomainException {
    private static final String FORMAT = "Độ dài %s vượt quá giới hạn (%d ký tự tối đa)";

    public EmailContentTooLongException() {
        super(String.format(FORMAT, "nội dung email", 900000), DomainErrorCode.EMAIL_CONTENT_TOO_LONG);
    }
}
