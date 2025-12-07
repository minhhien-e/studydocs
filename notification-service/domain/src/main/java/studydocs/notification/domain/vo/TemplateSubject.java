package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.template.InvalidTemplateSubjectException;

public record TemplateSubject(String value) {
    public TemplateSubject {
        if (value == null || value.isBlank()) {
            throw new InvalidTemplateSubjectException();
        }
    }
}
