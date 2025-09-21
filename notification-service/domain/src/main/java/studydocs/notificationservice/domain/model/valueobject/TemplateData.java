package studydocs.notificationservice.domain.model.valueobject;

import java.util.Map;
import java.util.Objects;

public record TemplateData(Map<String, Object> data) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TemplateData that = (TemplateData) o;
        return Objects.equals(data, that.data);
    }

}
