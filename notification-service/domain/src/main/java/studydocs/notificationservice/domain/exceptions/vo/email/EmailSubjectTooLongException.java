package studydocs.notificationservice.domain.exceptions.vo.email;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class EmailSubjectTooLongException extends DomainException {
    private static final String FORMAT = "Độ dài %s vượt quá giới hạn (%d ký tự tối đa)";

    public EmailSubjectTooLongException() {
        super(String.format(FORMAT, "tiêu đề email", 78), DomainErrorCode.EMAIL_SUBJECT_TOO_LONG);
    }
}
