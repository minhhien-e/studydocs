package studydocs.notificationservice.domain.exceptions.vo.name;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class NameTooLongException extends DomainException {
    private static final String FORMAT = "Độ dài %s vượt quá giới hạn (%d ký tự tối đa)";

    public NameTooLongException(String type, int maxLength) {
        super(String.format(FORMAT, type, maxLength), DomainErrorCode.NAME_TOO_LONG);
    }
}
