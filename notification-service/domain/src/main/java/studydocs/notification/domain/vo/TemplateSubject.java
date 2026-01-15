package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.template.InvalidTemplateSubjectException;

public class TemplateSubject extends ValueObject<TemplateSubject> {
    private final String value;

    public TemplateSubject(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateSubjectException();
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
