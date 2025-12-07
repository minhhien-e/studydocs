package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.template.InvalidTemplateNameException;

public record TemplateName(String value) {
    public TemplateName {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateNameException();
        }
    }
}
