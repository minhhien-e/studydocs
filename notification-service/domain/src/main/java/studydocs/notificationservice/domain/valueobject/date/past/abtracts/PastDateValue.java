package studydocs.notificationservice.domain.valueobject.date.past.abtracts;

import studydocs.notificationservice.domain.valueobject.date.abstracts.DateValue;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.date.FutureDateNotAllowedException;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;

public abstract class PastDateValue extends DateValue {
    protected PastDateValue(String fieldName, String domainName, LocalDateTime value) {
        super(fieldName, domainName, value);
        if (LocalDateUtils.isFutureDate(value))
            throw new FutureDateNotAllowedException(value, fieldName);
    }
}
