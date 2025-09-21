package studydocs.notificationservice.domain.exceptions.vo.channel;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class InvalidChannelValueException extends DomainException {
    private static final String FORMAT = "Kênh '%s' không hợp lệ. Chỉ cho phép: %s";

    public InvalidChannelValueException(String value, String validValues) {
        super(String.format(FORMAT, value, validValues), DomainErrorCode.CHANNEL_INVALID);
    }
}
