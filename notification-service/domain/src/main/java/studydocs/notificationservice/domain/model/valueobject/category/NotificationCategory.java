package studydocs.notificationservice.domain.model.valueobject.category;

import studydocs.notificationservice.domain.enums.NotificationCategoryEnum;
import studydocs.notificationservice.domain.exceptions.vo.category.InvalidCategoryValueException;

import java.util.Objects;

public class NotificationCategory {
    private final String value;

    public NotificationCategory(String value) {
        if (!NotificationCategoryEnum.contains(value))
            throw new InvalidCategoryValueException(value, NotificationCategoryEnum.getValues());
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NotificationCategory that = (NotificationCategory) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
