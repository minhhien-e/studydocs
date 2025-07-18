package studydocs.notificationservice.shared.exception.business;

import studydocs.notificationservice.shared.enums.ErrorCode;
import studydocs.notificationservice.shared.exception.HttpException;

public class FutureDateNotAllowedException extends HttpException {
    public FutureDateNotAllowedException(String message) {
        super(400, ErrorCode.FUTURE_DATE_NOT_ALLOWED, String.format("%s không được đặt ở tương lai",message));
    }
}
