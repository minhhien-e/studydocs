package studydocs.notificationservice.domain.exceptions.vo.template.subject;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class SubjectTemplateTooLongException extends DomainException {
    private static final String FORMAT = "Độ dài %s vượt quá giới hạn (%d ký tự tối đa)";

    public SubjectTemplateTooLongException() {
        super(String.format(FORMAT, "tiêu dề mẫu thông báo", 100), DomainErrorCode.SUBJECT_TEMPLATE_TOO_LONG);
    }
}
