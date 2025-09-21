package studydocs.notificationservice.domain.model.valueobject.date.past;

import studydocs.notificationservice.domain.exceptions.entity.template.TemplateUpdateBeforeCreationException;
import studydocs.notificationservice.domain.model.valueobject.date.abstracts.PastTime;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class TemplateUpdatedTime extends PastTime {
    public TemplateUpdatedTime(LocalDateTime value, TemplateCreationTime createdTime) {
        super("cập nhật mẫu thông báo", value);
        if (LocalDateUtils.isBefore(value, createdTime.getValue()))
            throw new TemplateUpdateBeforeCreationException();
    }
}
