package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.template.InvalidTemplateTypeException;

public class TemplateType extends ValueObject<TemplateType> {
    private final String value;

    public TemplateType(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateTypeException();
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
