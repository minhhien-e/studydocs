package studydocs.notificationservice.domain.model.valueobject.date.past;

import studydocs.notificationservice.domain.model.valueobject.date.abstracts.PastTime;

import java.time.LocalDateTime;

public class TemplateCreationTime extends PastTime {
    public TemplateCreationTime(LocalDateTime value) {
        super("tạo mẫu thông báo", value);
    }
}