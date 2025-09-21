package studydocs.notificationservice.domain.model.valueobject.date.past;

import studydocs.notificationservice.domain.model.valueobject.date.abstracts.PastTime;

import java.time.LocalDateTime;

public class NotificationCreationTime extends PastTime {
    public NotificationCreationTime(LocalDateTime value) {
        super("tạo thông báo", value);
    }
}
