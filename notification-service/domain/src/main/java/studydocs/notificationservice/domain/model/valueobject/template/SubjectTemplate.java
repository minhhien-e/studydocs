package studydocs.notificationservice.domain.model.valueobject.template;

import studydocs.notificationservice.domain.exceptions.vo.template.subject.SubjectTemplateTooLongException;
import studydocs.notificationservice.shared.utils.StringUtils;

public record SubjectTemplate(String value) {
    public SubjectTemplate {
        if (value.length() > 100) throw new SubjectTemplateTooLongException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubjectTemplate that = (SubjectTemplate) o;
        return StringUtils.equalsIgnoreCase(value, that.value);
    }

}
