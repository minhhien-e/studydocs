package studydocs.notification.domain.vo;

import java.time.LocalDateTime;

import studydocs.notification.domain.exception.template.InvalidTemplateUpdateTimeException;

public record TemplateUpdateTime(LocalDateTime value) {
    public TemplateUpdateTime {
        if (value == null) {
            throw new InvalidTemplateUpdateTimeException();
        }
    }
}
