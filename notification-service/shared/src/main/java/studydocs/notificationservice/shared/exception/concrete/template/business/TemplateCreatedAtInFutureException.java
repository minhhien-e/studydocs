package studydocs.notificationservice.shared.exception.concrete.template.business;

import studydocs.notificationservice.shared.exception.business.FutureDateNotAllowedException;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class TemplateCreatedAtInFutureException extends FutureDateNotAllowedException {
    public TemplateCreatedAtInFutureException(LocalDateTime createdAt) {
        super("Thời gian tạo '" + LocalDateUtils.getDateTimeFormat(LocalDateUtils.DATE_TIME_FORMAT, createdAt) + "'. Dữ liệu này");
    }
}
