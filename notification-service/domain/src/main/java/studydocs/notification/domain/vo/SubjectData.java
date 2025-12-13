package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.template.InvalidSubjectDataException;

import java.util.Map;

public record SubjectData(Map<String, String> value) {
    public SubjectData {
        if (value == null) {
            throw new InvalidSubjectDataException();
        }
    }
}
