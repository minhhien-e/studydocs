package studydocs.notificationservice.shared.exception.concrete.template.business;

import studydocs.notificationservice.shared.exception.business.BusinessRuleViolationException;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class InvalidUpdateDateInTemplateException extends BusinessRuleViolationException {
    public InvalidUpdateDateInTemplateException(LocalDateTime createdAt, LocalDateTime updatedAt) {
        super("Thời gian cập nhật (" + LocalDateUtils.getDateTimeFormat(LocalDateUtils.DATE_TIME_FORMAT, updatedAt) + ") không thể trước thời gian tạo (" + LocalDateUtils.getDateTimeFormat(LocalDateUtils.DATE_TIME_FORMAT, createdAt) + ").");
    }
}
