package studydocs.notificationservice.domain.model.valueobject.date.past;

import studydocs.notificationservice.domain.model.valueobject.date.abstracts.PastTime;

import java.time.LocalDateTime;

public class NotificationDeletionTime extends PastTime {
    public NotificationDeletionTime(LocalDateTime value) {
        super("xóa thông báo", value);
    }
}
