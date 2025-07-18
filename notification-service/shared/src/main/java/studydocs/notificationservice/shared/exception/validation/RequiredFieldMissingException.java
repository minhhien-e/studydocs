package studydocs.notificationservice.shared.exception.validation;


import studydocs.notificationservice.shared.enums.ErrorCode;
import studydocs.notificationservice.shared.exception.HttpException;

public class RequiredFieldMissingException extends HttpException {
    public RequiredFieldMissingException(String message) {
        super(400, ErrorCode.REQUIRED_FIELD_MISSING, String.format("%s là bắt buộc.", message));
    }
}
