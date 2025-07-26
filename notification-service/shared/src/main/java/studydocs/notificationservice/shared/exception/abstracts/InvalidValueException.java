package studydocs.notificationservice.shared.exception.abstracts;

import studydocs.notificationservice.shared.enums.ErrorCode;

public abstract class InvalidValueException extends HttpException {
    public InvalidValueException(String message) {
        super(400, ErrorCode.INVALID_VALUE, String.format("Giá trị %s không phù hợp.", message));
    }
}
