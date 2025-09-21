package studydocs.notificationservice.domain.exceptions.vo.category;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class InvalidCategoryValueException extends DomainException {
    private static final String FORMAT = "Loại '%s' không hợp lệ. Chỉ cho phép: %s";

    public InvalidCategoryValueException(String value, String validValues) {
        super(String.format(FORMAT, value, validValues), DomainErrorCode.CHANNEL_INVALID);
    }
}
