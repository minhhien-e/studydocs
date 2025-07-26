package studydocs.notificationservice.domain.valueobject.date.abstracts;

import studydocs.notificationservice.shared.exception.concrete.valueobjects.date.MissingDateFieldException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class DateValue {
    private LocalDateTime value;

    protected DateValue(String fieldName, String domainName, LocalDateTime value) {
        if (value == null)
            throw new MissingDateFieldException(fieldName, domainName);
    }

    protected void init(LocalDateTime value) {
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DateValue dateValue = (DateValue) o;
        return Objects.equals(value, dateValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
