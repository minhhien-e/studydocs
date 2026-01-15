package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.template.InvalidTemplateChannelException;

public class TemplateChannel extends ValueObject<TemplateChannel> {
    private final String value;

    public TemplateChannel(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateChannelException();
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }
}
