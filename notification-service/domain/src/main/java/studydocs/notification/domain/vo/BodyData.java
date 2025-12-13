package studydocs.notification.domain.vo;

import java.util.Map;

import studydocs.notification.domain.exception.template.InvalidBodyDataException;

public record BodyData(Map<String, String> value) {
    public BodyData {
        if (value == null) {
            throw new InvalidBodyDataException();
        }
    }
}
