package studydocs.notification.domain.vo;

import java.time.LocalDateTime;

import studydocs.notification.domain.exception.template.InvalidTemplateCreationTimeException;

public record TemplateCreationTime (LocalDateTime value){
    public TemplateCreationTime {
        if (value == null) {
            throw new InvalidTemplateCreationTimeException();
        }
    }
}
