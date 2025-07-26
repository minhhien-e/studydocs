package studydocs.notificationservice.shared.exception.abstracts;


import studydocs.notificationservice.shared.enums.ErrorCode;

public abstract class RequiredFieldMissingException extends HttpException {
    public RequiredFieldMissingException(String message) {
        super(400, ErrorCode.REQUIRED_FIELD_MISSING, String.format("%s là bắt buộc.", message));
    }
}
