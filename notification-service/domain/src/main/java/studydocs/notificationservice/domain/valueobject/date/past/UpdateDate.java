package studydocs.notificationservice.domain.valueobject.date.past;

import studydocs.notificationservice.domain.valueobject.date.past.abtracts.PastDateValue;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.date.InvalidUpdateDateException;
import studydocs.notificationservice.shared.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class UpdateDate extends PastDateValue {
    public UpdateDate(String fieldName, String domainName, LocalDateTime updateAt, LocalDateTime createAt) {
        super(fieldName, domainName, updateAt);
        if (LocalDateUtils.isAfter(updateAt, createAt))
            throw new InvalidUpdateDateException(updateAt, createAt);
        else init(updateAt);
    }
}
