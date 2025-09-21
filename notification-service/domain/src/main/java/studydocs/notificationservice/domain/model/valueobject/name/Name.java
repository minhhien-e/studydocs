package studydocs.notificationservice.domain.model.valueobject.name;

import studydocs.notificationservice.shared.utils.StringUtils;

import java.util.Objects;

public abstract class Name {
    protected String value;


    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Name that = (Name) o;
        return StringUtils.equalsIgnoreCase(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
