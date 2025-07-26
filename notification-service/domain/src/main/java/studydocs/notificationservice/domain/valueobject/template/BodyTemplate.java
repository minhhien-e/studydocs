package studydocs.notificationservice.domain.valueobject.template;

import studydocs.notificationservice.shared.exception.concrete.valueobjects.template.body.MissingBodyTemplateFieldException;
import studydocs.notificationservice.shared.utils.StringUtils;

public record BodyTemplate(String value) {
    public BodyTemplate {
        if (StringUtils.isNullOrBlank(value))
            throw new MissingBodyTemplateFieldException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BodyTemplate that = (BodyTemplate) o;
        return StringUtils.equalsIgnoreCase(value, that.value);
    }

}
