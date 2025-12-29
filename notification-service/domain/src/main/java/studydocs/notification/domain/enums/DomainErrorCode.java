package studydocs.notification.domain.enums;

public enum DomainErrorCode {

    /* ===================== NOT FOUND ===================== */
    NOTIFICATION_NOT_FOUND(0, DomainErrorCategory.NOT_FOUND),
    TEMPLATE_BY_ID_NOT_FOUND(1, DomainErrorCategory.NOT_FOUND),
    TEMPLATE_BY_NAME_NOT_FOUND(2, DomainErrorCategory.NOT_FOUND),
    RECIPIENT_NOT_FOUND(3, DomainErrorCategory.NOT_FOUND),
    RECIPIENTS_NOT_FOUND(4, DomainErrorCategory.NOT_FOUND),
    SENDER_NOT_FOUND(5, DomainErrorCategory.NOT_FOUND),
    USER_NOTIFICATION_PROFILE_NOT_FOUND(6, DomainErrorCategory.NOT_FOUND),
    NOTIFICATION_RECIPIENT_NOT_FOUND(7, DomainErrorCategory.NOT_FOUND),

    /* ===================== VALIDATION ===================== */
    INVALID_NOTIFICATION_STATUS(8, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_TYPE(9, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_CHANNEL(10, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_CREATION_TIME(11, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_DELETION_TIME(12, DomainErrorCategory.VALIDATION),

    INVALID_TEMPLATE_NAME(13, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_SUBJECT(14, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_BODY(15, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_DESCRIPTION(16, DomainErrorCategory.VALIDATION),
    TEMPLATE_DESCRIPTION_NULL_OR_EMPTY(17, DomainErrorCategory.VALIDATION),
    TEMPLATE_DESCRIPTION_TOO_SHORT(18, DomainErrorCategory.VALIDATION),
    TEMPLATE_DESCRIPTION_TOO_LONG(19, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_CHANNEL(20, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_CREATION_TIME(21, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_UPDATE_TIME(22, DomainErrorCategory.VALIDATION),
    INVALID_TEMPLATE_TYPE(23, DomainErrorCategory.VALIDATION),

    INVALID_BODY_DATA(24, DomainErrorCategory.VALIDATION),
    INVALID_PERSONALIZED_DATA(25, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_SNAPSHOT_SUBJECT(26, DomainErrorCategory.VALIDATION),
    INVALID_NOTIFICATION_SNAPSHOT_BODY(27, DomainErrorCategory.VALIDATION),

    INVALID_FCM_TOKEN(28, DomainErrorCategory.VALIDATION),
    INVALID_EMAIL_ADDRESS(37, DomainErrorCategory.VALIDATION),
    INVALID_PHONE_NUMBER(38, DomainErrorCategory.VALIDATION),

    /* ===================== CONFLICT ===================== */
    TEMPLATE_ALREADY_EXISTS(29, DomainErrorCategory.CONFLICT),
    DUPLICATE_FCM_TOKEN(30, DomainErrorCategory.CONFLICT),
    NOTIFICATION_ALREADY_SOFT_DELETED(31, DomainErrorCategory.CONFLICT),
    NOTIFICATION_RECIPIENT_ALREADY_EXISTS(32, DomainErrorCategory.CONFLICT),
    USER_NOTIFICATION_PROFILE_ALREADY_EXISTS(33, DomainErrorCategory.CONFLICT),
    NOTIFICATION_NOT_SOFT_DELETED(34, DomainErrorCategory.CONFLICT),
    NOTIFICATION_RECIPIENT_DELETED(35, DomainErrorCategory.CONFLICT),

    /* ===================== FORBIDDEN ===================== */
    ACCESS_DENIED(36, DomainErrorCategory.FORBIDDEN);

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
