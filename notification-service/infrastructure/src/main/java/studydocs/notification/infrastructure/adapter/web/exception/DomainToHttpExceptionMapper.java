package studydocs.notification.infrastructure.adapter.web.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;
import studydocs.notification.infrastructure.enums.HttpErrorCategoryMapping;
import studydocs.notification.shared.web.HttpException;

@Component
public class DomainToHttpExceptionMapper {
    @Value("${app.error.code}")
    private int baseErrorCode;


    public HttpException map(DomainException exception) {
        DomainErrorCode errorCode = exception.getErrorCode();
        HttpStatus status =
                HttpErrorCategoryMapping.from(errorCode.getCategory());

        return new HttpException(exception.getMessage(), status.value(), baseErrorCode + errorCode.getValue());
    }
}
