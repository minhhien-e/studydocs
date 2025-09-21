package studydocs.notificationservice.domain.exceptions.entity.template;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class TemplateUpdateBeforeCreationException extends DomainException {
    private static final String MESSAGE = "Thời gian cập nhật mẫu thông báo không được trước thời gian tạo mẫu.";

    public TemplateUpdateBeforeCreationException() {
        super(MESSAGE, DomainErrorCode.TEMPLATE_UPDATE_BEFORE_CREATION);
    }
}
