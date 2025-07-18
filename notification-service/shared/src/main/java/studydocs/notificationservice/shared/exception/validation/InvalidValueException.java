package studydocs.notificationservice.shared.exception.validation;

import studydocs.notificationservice.shared.enums.ErrorCode;
import studydocs.notificationservice.shared.exception.HttpException;

public class InvalidValueException extends HttpException {
    public InvalidValueException(String message) {
        super(400, ErrorCode.INVALID_VALUE, String.format("Giá trị %s không phù hợp.", message));
    }
}
