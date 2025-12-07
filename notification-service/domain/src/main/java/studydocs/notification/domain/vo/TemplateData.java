package studydocs.notification.domain.vo;

import java.util.Map;

import studydocs.notification.domain.exception.template.InvalidTemplateDataException;

public record TemplateData(Map<String, String> value) {
    public TemplateData {
        if (value == null) {
            throw new InvalidTemplateDataException();
        }
    }
}
