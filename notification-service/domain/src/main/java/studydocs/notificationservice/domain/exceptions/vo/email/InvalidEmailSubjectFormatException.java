package studydocs.notificationservice.domain.exceptions.vo.email;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class InvalidEmailSubjectFormatException extends DomainException {
    private static final String FORMAT = "Tiêu đề email '%s' không hợp lệ. Vui lòng nhập đúng định dạng (không chứa khoảng trắng)";

    public InvalidEmailSubjectFormatException(String email) {
        super(String.format(FORMAT, email), DomainErrorCode.EMAIL_SUBJECT_INVALID);
    }
}
