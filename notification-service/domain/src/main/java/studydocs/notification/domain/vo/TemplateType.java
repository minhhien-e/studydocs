package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.template.InvalidTemplateTypeException;

public record TemplateType(String value)  {
    public TemplateType {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateTypeException();
        }
    }
}
