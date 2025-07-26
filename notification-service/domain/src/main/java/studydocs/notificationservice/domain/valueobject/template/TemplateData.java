package studydocs.notificationservice.domain.valueobject.template;

import studydocs.notificationservice.shared.exception.concrete.valueobjects.template.data.MissingTemplateDataFieldException;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.template.data.MissingTemplateDataKeyException;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.template.data.MissingTemplateDataValueException;
import studydocs.notificationservice.shared.utils.MapUtils;

import java.util.Map;
import java.util.Objects;

public record TemplateData(Map<String, Object> data) {
    public TemplateData {
        if (MapUtils.isNullOrEmpty(data))
            throw new MissingTemplateDataFieldException();
        if (MapUtils.hasNullKeys(data))
            throw new MissingTemplateDataKeyException();
        if (MapUtils.hasNullValues(data))
            throw new MissingTemplateDataValueException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TemplateData that = (TemplateData) o;
        return Objects.equals(data, that.data);
    }

}
