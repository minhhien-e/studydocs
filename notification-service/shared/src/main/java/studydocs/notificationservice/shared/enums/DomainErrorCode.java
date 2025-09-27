package studydocs.notificationservice.shared.enums;


import studydocs.notificationservice.shared.exception.annotations.HttpStatusCode;

public enum DomainErrorCode {

    //Name
    @HttpStatusCode(400)
    NAME_TOO_LONG,
    @HttpStatusCode(400)
    NAME_ALREADY_EXISTS,

    //Channel
    @HttpStatusCode(400)
    CHANNEL_INVALID,

    //Category
    @HttpStatusCode(400)
    CATEGORY_INVALID,

    //Body template
    @HttpStatusCode(400)
    BODY_TEMPLATE_TOO_LONG,

    //Subject Template
    @HttpStatusCode(400)
    SUBJECT_TEMPLATE_TOO_LONG,

    //Email
    @HttpStatusCode(400)
    EMAIL_INVALID,
    @HttpStatusCode(400)
    EMAIL_SUBJECT_INVALID,
    @HttpStatusCode(400)
    EMAIL_SUBJECT_TOO_LONG,
    @HttpStatusCode(400)
    EMAIL_CONTENT_TOO_LONG,

    //Time
    @HttpStatusCode(400)
    DATE_PAST_INVALID,

    //Notification
    @HttpStatusCode(409) // conflict
            NOTIFICATION_ALREADY_DELETED,
    @HttpStatusCode(409) // conflict
    NOTIFICATION_ALREADY_SENT,
    @HttpStatusCode(404) // not found
    NOTIFICATION_NOT_FOUND,
    @HttpStatusCode(404) // empty list treated as not found
    EMPTY_NOTIFICATION_LIST,
    @HttpStatusCode(409) // conflict
    NOTIFICATION_NOT_DELETED,

    //Template
    @HttpStatusCode(400)
    TEMPLATE_UPDATE_BEFORE_CREATION
}
