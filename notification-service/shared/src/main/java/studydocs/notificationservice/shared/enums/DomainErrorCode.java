package studydocs.notificationservice.shared.enums;

public enum DomainErrorCode {
    //Name
    NAME_TOO_LONG,
    NAME_ALREADY_EXISTS,
    //Channel
    CHANNEL_INVALID,
    //Category
    CATEGORY_INVALID,
    //Body template
    BODY_TEMPLATE_TOO_LONG,
    //Subject Template
    SUBJECT_TEMPLATE_TOO_LONG,
    //Email
    EMAIL_INVALID,
    EMAIL_SUBJECT_INVALID,
    EMAIL_SUBJECT_TOO_LONG,
    EMAIL_CONTENT_TOO_LONG,
    //Time
    DATE_PAST_INVALID,
    //Notification
    NOTIFICATION_ALREADY_DELETED,
    TEMPLATE_UPDATE_BEFORE_CREATION
}
