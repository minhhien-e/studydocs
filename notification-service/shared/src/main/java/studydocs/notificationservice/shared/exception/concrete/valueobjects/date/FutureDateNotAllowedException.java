package studydocs.notificationservice.shared.exception.concrete.valueobjects.date;

import studydocs.notificationservice.shared.enums.ErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.HttpException;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class FutureDateNotAllowedException extends HttpException {
    private static final String format = "Thời gian %s (%s). Dữ liệu này không được đặt ở tương lai";
    public FutureDateNotAllowedException(LocalDateTime dateTime, String type) {
        super(400, ErrorCode.FUTURE_DATE_NOT_ALLOWED, String.format(format, type, LocalDateUtils.getDateTimeFormat(LocalDateUtils.DATE_TIME_FORMAT, dateTime)));
    }
}
