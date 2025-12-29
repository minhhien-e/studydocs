package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.template.InvalidTemplateCreationTimeException;

import java.time.LocalDateTime;

public class TemplateCreationTime extends ValueObject<TemplateCreationTime> {
    private final LocalDateTime value;

    public TemplateCreationTime(LocalDateTime value) {
        if (value == null) {
            throw new InvalidTemplateCreationTimeException();
        }
        this.value = value;
    }

    public LocalDateTime value() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }
}
