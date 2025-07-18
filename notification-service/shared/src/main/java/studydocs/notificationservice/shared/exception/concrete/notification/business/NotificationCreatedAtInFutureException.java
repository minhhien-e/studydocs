package studydocs.notificationservice.shared.exception.concrete.notification.business;

import studydocs.notificationservice.shared.exception.business.FutureDateNotAllowedException;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class NotificationCreatedAtInFutureException extends FutureDateNotAllowedException {
    public NotificationCreatedAtInFutureException(LocalDateTime createdAt) {
        super("Thời gian tạo '" + LocalDateUtils.getDateTimeFormat(LocalDateUtils.DATE_TIME_FORMAT, createdAt) + "'. Dữ liệu này");
    }
}
