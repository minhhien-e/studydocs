package studydocs.notification.infrastructure.adapter.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;
import studydocs.notification.infrastructure.enums.HttpErrorCategoryMapping;
import studydocs.notification.shared.web.HttpException;

@Component
public class DomainToHttpExceptionMapper {

    public HttpException map(DomainException exception) {
        DomainErrorCode errorCode = exception.getErrorCode();
        HttpStatus status =
                HttpErrorCategoryMapping.from(errorCode.getCategory());
        return new HttpException(exception.getMessage(), status.value(), errorCode.getValue());
    }
}
