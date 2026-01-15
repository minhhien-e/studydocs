package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.template.InvalidTemplateNameException;

public class TemplateName extends ValueObject<TemplateName> {
    private final String value;

    public TemplateName(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateNameException();
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
