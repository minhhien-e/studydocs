package studydocs.notificationservice.domain.model.valueobject.template;

import studydocs.notificationservice.domain.exceptions.vo.template.subject.SubjectTemplateTooLongException;
import studydocs.notificationservice.shared.utils.StringUtils;

public record TemplateSubject(String value) {
    public TemplateSubject {
        if (value.length() > 100) throw new SubjectTemplateTooLongException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TemplateSubject that = (TemplateSubject) o;
        return StringUtils.equalsIgnoreCase(value, that.value);
    }

}
