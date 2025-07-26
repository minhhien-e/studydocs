package studydocs.notificationservice.domain.valueobject;

import studydocs.notificationservice.shared.exception.concrete.valueobjects.name.MissingNameFieldException;
import studydocs.notificationservice.shared.utils.StringUtils;

import java.util.Objects;

public class Name {
    private final String value;

    public Name(String value, String domainName) {
        if (StringUtils.isNullOrBlank(value))
            throw new MissingNameFieldException(domainName);
        this.value = value;
    }

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
