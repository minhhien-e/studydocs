package studydocs.notificationservice.domain.exceptions.vo.name;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class NameAlreadyExistsException extends DomainException {

    private static final String MESSAGE = "Tên '%s' đã tồn tại. Vui lòng chọn tên khác.";

    public NameAlreadyExistsException(String name) {
        super(String.format(MESSAGE, name), DomainErrorCode.NAME_ALREADY_EXISTS);
    }
}
