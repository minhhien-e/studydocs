package studydocs.notificationservice.domain.model.valueobject.date.abstracts;

import studydocs.notificationservice.domain.exceptions.vo.time.InvalidPastDateException;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;

public abstract class PastTime extends DateTime {
    protected PastTime(String type, LocalDateTime value) {
        super(value);
        if (LocalDateUtils.isFutureDate(value))
            throw new InvalidPastDateException(type, value);
    }
}
