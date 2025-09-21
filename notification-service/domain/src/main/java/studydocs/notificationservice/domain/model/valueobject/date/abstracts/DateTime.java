package studydocs.notificationservice.domain.model.valueobject.date.abstracts;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class DateTime {
    private final LocalDateTime value;

    protected DateTime(LocalDateTime value) {
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DateTime dateValue = (DateTime) o;
        return Objects.equals(value, dateValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
