package studydocs.notification.infrastructure.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import studydocs.notification.domain.enums.DomainErrorCategory;

@Getter
public enum HttpErrorCategoryMapping {
    VALIDATION(HttpStatus.BAD_REQUEST),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    CONFLICT(HttpStatus.CONFLICT),
    FORBIDDEN(HttpStatus.FORBIDDEN),
    SYSTEM(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;

    HttpErrorCategoryMapping(HttpStatus status) {
        this.status = status;
    }

    public static HttpStatus from(Object category) {
        if (category instanceof DomainErrorCategory domain) {
            return valueOf(domain.name()).status;
        } else if (category instanceof InfrastructureErrorCode infrastructure) {
            return valueOf(infrastructure.name()).status;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
