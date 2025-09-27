package studydocs.notificationservice.shared.helper;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.enums.InfrastructureErrorCode;
import studydocs.notificationservice.shared.exception.annotations.HttpStatusCode;

import java.lang.reflect.Field;

public class HttpStatusCodeExtractor {
    public static Integer extractFromDomain(DomainErrorCode code) {
        try {
            Field field = code.getClass().getField(code.name());
            HttpStatusCode annotation = field.getAnnotation(HttpStatusCode.class);
            return annotation != null ? annotation.value() : 500;
        } catch (NoSuchFieldException e) {
            return 500;
        }
    }

    public static Integer extractFromInfrastructure(InfrastructureErrorCode code) {
        try {
            Field field = code.getClass().getField(code.name());
            HttpStatusCode annotation = field.getAnnotation(HttpStatusCode.class);
            return annotation != null ? annotation.value() : 500;
        } catch (NoSuchFieldException e) {
            return 500;
        }
    }
}
