package studydocs.notification.domain.enums;

public enum DomainErrorCode {

    /* ===================== NOT FOUND ===================== */
    NOTIFICATION_NOT_FOUND(1, DomainErrorCategory.NOT_FOUND),
    TEMPLATE_BY_ID_NOT_FOUND(10, DomainErrorCategory.NOT_FOUND),
    TEMPLATE_BY_NAME_NOT_FOUND(50, DomainErrorCategory.NOT_FOUND),
    RECIPIENT_NOT_FOUND(20, DomainErrorCategory.NOT_FOUND),
    RECIPIENTS_NOT_FOUND(21, DomainErrorCategory.NOT_FOUND),
    SENDER_NOT_FOUND(22, DomainErrorCategory.NOT_FOUND),
    USER_NOTIFICATION_PROFILE_NOT_FOUND(40, DomainErrorCategory.NOT_FOUND),
    NOTIFICATION_RECIPIENT_NOT_FOUND(45, DomainErrorCategory.NOT_FOUND),

    /* ===================== VALIDATION ===================== */
    INVALID_NOTIFICATION_STATUS(2, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_TYPE(5, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_CHANNEL(6, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_CREATION_TIME(8, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_DELETION_TIME(9, DomainErrorCategory.VALIDATION),

    INVALID_TEMPLATE_NAME(12, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_SUBJECT(13, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_BODY(14, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_DESCRIPTION(15, DomainErrorCategory.VALIDATION),
    TEMPLATE_DESCRIPTION_NULL_OR_EMPTY(29, DomainErrorCategory.VALIDATION),
    TEMPLATE_DESCRIPTION_TOO_SHORT(30, DomainErrorCategory.VALIDATION),
    TEMPLATE_DESCRIPTION_TOO_LONG(31, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_CHANNEL(17, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_CREATION_TIME(18, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_UPDATE_TIME(19, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_TYPE(51, DomainErrorCategory.VALIDATION),

    INVALID_BODY_DATA(16, DomainErrorCategory.VALIDATION),
    INVALID_PERSONALIZED_DATA(23, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_SNAPSHOT_SUBJECT(26, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_SNAPSHOT_BODY(27, DomainErrorCategory.VALIDATION),

    INVALID_FCM_TOKEN(42, DomainErrorCategory.VALIDATION),

    /* ===================== CONFLICT ===================== */
    TEMPLATE_ALREADY_EXISTS(11, DomainErrorCategory.CONFLICT),
    DUPLICATE_FCM_TOKEN(41, DomainErrorCategory.CONFLICT),
    NOTIFICATION_ALREADY_SOFT_DELETED(3, DomainErrorCategory.CONFLICT),
    NOTIFICATION_RECIPIENT_ALREADY_EXISTS(43, DomainErrorCategory.CONFLICT),
    USER_NOTIFICATION_PROFILE_ALREADY_EXISTS(44, DomainErrorCategory.CONFLICT),
    NOTIFICATION_NOT_SOFT_DELETED(4, DomainErrorCategory.CONFLICT),
    NOTIFICATION_RECIPIENT_DELETED(28, DomainErrorCategory.CONFLICT),

    /* ===================== FORBIDDEN ===================== */
    ACCESS_DENIED(24, DomainErrorCategory.FORBIDDEN);

    private final Integer value;
    private final DomainErrorCategory category;

    DomainErrorCode(Integer value, DomainErrorCategory category) {
        this.value = value;
        this.category = category;
    }

    public Integer getValue() {
        return value;
    }

    public DomainErrorCategory getCategory() {
        return category;
    }
}
