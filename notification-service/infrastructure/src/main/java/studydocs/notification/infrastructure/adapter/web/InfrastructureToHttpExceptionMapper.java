package studydocs.notification.infrastructure.adapter.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import studydocs.notification.infrastructure.enums.HttpErrorCategoryMapping;
import studydocs.notification.infrastructure.enums.InfrastructureErrorCode;
import studydocs.notification.infrastructure.exception.base.InfrastructureException;
import studydocs.notification.shared.web.HttpException;

@Component
public class InfrastructureToHttpExceptionMapper {

    public HttpException map(InfrastructureException exception) {
        InfrastructureErrorCode errorCode = exception.getErrorCode();
        HttpStatus status =
                HttpErrorCategoryMapping.from(errorCode.getCategory());
        return new HttpException(exception.getMessage(), status.value(), errorCode.getValue());
    }
}
