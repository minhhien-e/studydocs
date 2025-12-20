package studydocs.notification.infrastructure.enums;

import lombok.Getter;

@Getter
public enum InfrastructureErrorCode {
    NOTIFICATION_RECIPIENT_NOT_FOUND(43, InfrastructureErrorCategory.NOT_FOUND),
    SEND_FAILED(7, InfrastructureErrorCategory.SYSTEM);
    private final Integer value;
    private final InfrastructureErrorCategory category;

    InfrastructureErrorCode(Integer value, InfrastructureErrorCategory category) {
        this.value = value;
        this.category = category;
    }

}
