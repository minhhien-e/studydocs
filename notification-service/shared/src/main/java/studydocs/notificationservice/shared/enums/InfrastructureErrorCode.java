package studydocs.notificationservice.shared.enums;

import studydocs.notificationservice.shared.exception.annotations.HttpStatusCode;

public enum InfrastructureErrorCode {
    @HttpStatusCode(404)
    RESOURCE_NOT_FOUND,
    @HttpStatusCode(500)
    DATABASE_UPDATE_FAILURE,
    @HttpStatusCode(500)
    EMAIL_SEND_FAILURE,
}
