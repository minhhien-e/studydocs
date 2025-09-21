package studydocs.notificationservice.domain.model.valueobject.template;

import studydocs.notificationservice.domain.exceptions.vo.template.body.BodyTemplateTooLongException;
import studydocs.notificationservice.shared.utils.StringUtils;

public record BodyTemplate(String value) {
    public BodyTemplate {
        if (value.length() > 2000) throw new BodyTemplateTooLongException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BodyTemplate that = (BodyTemplate) o;
        return StringUtils.equalsIgnoreCase(value, that.value);
    }

}
