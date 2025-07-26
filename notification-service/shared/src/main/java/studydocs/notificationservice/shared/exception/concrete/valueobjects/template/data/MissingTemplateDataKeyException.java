package studydocs.notificationservice.shared.exception.concrete.valueobjects.template.data;

import studydocs.notificationservice.shared.exception.abstracts.BusinessRuleViolationException;

public class MissingTemplateDataKeyException extends BusinessRuleViolationException {
    public MissingTemplateDataKeyException() {
        super("Khóa trong dữ liệu để tạo thông báo không được để trống (null).");
    }
}
