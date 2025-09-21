package studydocs.notificationservice.domain.exceptions.vo.template.body;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class BodyTemplateTooLongException extends DomainException {
    private static final String FORMAT = "Độ dài %s vượt quá giới hạn (%d ký tự tối đa)";

    public BodyTemplateTooLongException() {
        super(String.format(FORMAT, "khung mẫu thông báo", 2000), DomainErrorCode.BODY_TEMPLATE_TOO_LONG);
    }
}
