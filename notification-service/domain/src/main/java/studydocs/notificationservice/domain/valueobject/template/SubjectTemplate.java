package studydocs.notificationservice.domain.valueobject.template;

import studydocs.notificationservice.shared.exception.concrete.valueobjects.template.subject.MissingSubjectTemplateFieldException;
import studydocs.notificationservice.shared.utils.StringUtils;

public record SubjectTemplate(String value) {
    public SubjectTemplate {
        if (StringUtils.isNullOrBlank(value))
            throw new MissingSubjectTemplateFieldException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubjectTemplate that = (SubjectTemplate) o;
        return StringUtils.equalsIgnoreCase(value, that.value);
    }

}
