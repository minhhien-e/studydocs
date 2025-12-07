package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.template.InvalidTemplateBodyException;

public record TemplateBody(String value) {
    public TemplateBody {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateBodyException();
        }
    }
}
