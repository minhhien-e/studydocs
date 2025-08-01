package studydocs.notificationservice.shared.exception.concrete.valueobjects.date;

import studydocs.notificationservice.shared.exception.abstracts.BusinessRuleViolationException;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class InvalidUpdateDateException extends BusinessRuleViolationException {
    private static final String format = "Thời gian cập nhật (%s) không thể trước thời gian tạo (%s)";

    public InvalidUpdateDateException(LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(String.format(format,LocalDateUtils.getDateTimeFormat(LocalDateUtils.DATE_TIME_FORMAT, updatedAt),LocalDateUtils.getDateTimeFormat(LocalDateUtils.DATE_TIME_FORMAT, createdAt)));
    }
}
