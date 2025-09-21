package studydocs.notificationservice.domain.model.valueobject.channel;


import java.util.Objects;

public abstract class Channel {
    protected String value;

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Channel that = (Channel) o;
        return value.equalsIgnoreCase(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
