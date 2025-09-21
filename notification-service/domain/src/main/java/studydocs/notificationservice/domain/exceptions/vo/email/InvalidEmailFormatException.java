package studydocs.notificationservice.domain.exceptions.vo.email;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class InvalidEmailFormatException extends DomainException {
    private static final String FORMAT = "Địa chỉ email '%s' không hợp lệ. Vui lòng nhập đúng định dạng (ví dụ: user@example.com)";

    public InvalidEmailFormatException(String email) {
        super(String.format(FORMAT, email), DomainErrorCode.EMAIL_INVALID);
    }
}
