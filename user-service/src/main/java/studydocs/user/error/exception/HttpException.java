package studydocs.user.error.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class HttpException extends RuntimeException {
    int statusCode;
    Integer errorCode;
}
