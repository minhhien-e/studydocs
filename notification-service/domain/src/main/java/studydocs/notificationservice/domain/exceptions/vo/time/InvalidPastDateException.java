package studydocs.notificationservice.domain.exceptions.vo.time;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

import java.time.LocalDateTime;

public class InvalidPastDateException extends DomainException {
    private static final String MESSAGE = "Thời gian %s '%s' không hợp lệ. Giá trị phải nằm trong quá khứ.";

    public InvalidPastDateException(String type, LocalDateTime value) {
        super(String.format(MESSAGE, type, value), DomainErrorCode.DATE_PAST_INVALID);
    }
}