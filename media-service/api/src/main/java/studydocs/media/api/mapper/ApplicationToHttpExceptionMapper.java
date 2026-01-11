package studydocs.media.api.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import studydocs.media.application.exception.ApplicationDomainException;
import studydocs.media.api.helper.HttpErrorCategoryMapping;
import studydocs.media.shared.web.HttpException;

@Component
public class ApplicationToHttpExceptionMapper {
    @Value("${app.error.code}")
    private int baseErrorCode;

    public HttpException map(ApplicationDomainException exception) {
        HttpStatus status = HttpErrorCategoryMapping.from(exception.getCategory());

        return new HttpException(exception.getMessage(), status.value(), baseErrorCode + exception.getCode());
    }
}
