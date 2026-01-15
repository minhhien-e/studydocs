package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.template.InvalidTemplateBodyException;

public class TemplateBody extends ValueObject<TemplateBody> {
    private final String value;

    public TemplateBody(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateBodyException();
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
