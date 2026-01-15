package studydocs.media.api.helper;

import lombok.Getter;
import org.springframework.http.HttpStatus;


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

    public static HttpStatus from(String category) {
        try {
            return valueOf(category).status;
        } catch (IllegalArgumentException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
