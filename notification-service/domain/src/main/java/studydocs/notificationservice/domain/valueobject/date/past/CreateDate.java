package studydocs.notificationservice.domain.valueobject.date.past;

import studydocs.notificationservice.domain.valueobject.date.past.abtracts.PastDateValue;

import java.time.LocalDateTime;

public class CreateDate extends PastDateValue {
    public CreateDate(String fieldName, String domainName, LocalDateTime value) {
        super(fieldName, domainName, value);
        init(value);
    }
}
