package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.template.InvalidTemplateUpdateTimeException;

import java.time.LocalDateTime;

public class TemplateUpdateTime extends ValueObject<TemplateUpdateTime> {
    private final LocalDateTime value;

    public TemplateUpdateTime(LocalDateTime value) {
        if (value == null) {
            throw new InvalidTemplateUpdateTimeException();
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
