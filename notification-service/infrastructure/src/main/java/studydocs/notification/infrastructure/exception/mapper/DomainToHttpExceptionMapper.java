package studydocs.notification.infrastructure.exception.mapper;

import org.springframework.stereotype.Component;
import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;
import studydocs.notification.infrastructure.exception.HttpException;

@Component
public class DomainToHttpExceptionMapper {

    public HttpException map(DomainException exception) {
        DomainErrorCode domainErrorCode = exception.getErrorCode();

        int statusCode = switch (domainErrorCode) {
            case NOTIFICATION_NOT_FOUND, TEMPLATE_NOT_FOUND, RECIPIENT_NOT_FOUND,
                 SENDER_NOT_FOUND, RECIPIENTS_NOT_FOUND, USER_NOTIFICATION_PROFILE_NOT_FOUND -> 404;
            case ACCESS_DENIED -> 403;
            case INVALID_NOTIFICATION_STATUS, TEMPLATE_ALREADY_EXISTS,
                 INVALID_TEMPLATE_DESCRIPTION, NOTIFICATION_ALREADY_SOFT_DELETED,
                 NOTIFICATION_NOT_SOFT_DELETED, INVALID_NOTIFICATION_TYPE,
                 INVALID_NOTIFICATION_CHANNEL, INVALID_TEMPLATE_BODY,
                 INVALID_TEMPLATE_SUBJECT, INVALID_TEMPLATE_NAME,
                 INVALID_BODY_DATA, INVALID_PERSONALIZED_DATA,
                 INVALID_TEMPLATE_CHANNEL, INVALID_NOTIFICATION_CREATION_TIME,
                 INVALID_NOTIFICATION_DELETION_TIME, INVALID_TEMPLATE_CREATION_TIME,
                 INVALID_TEMPLATE_UPDATE_TIME, TEMPLATE_DESCRIPTION_NULL_OR_EMPTY,
                 TEMPLATE_DESCRIPTION_TOO_SHORT, TEMPLATE_DESCRIPTION_TOO_LONG,
                 INVALID_NOTIFICATION_SNAPSHOT_SUBJECT, INVALID_NOTIFICATION_SNAPSHOT_BODY,
                 DUPLICATE_FCM_TOKEN, INVALID_FCM_TOKEN, NOTIFICATION_RECIPIENT_DELETED -> 400;
            default -> 500;
        };

        return new HttpException(exception.getMessage(), statusCode, domainErrorCode.getValue());
    }
}
