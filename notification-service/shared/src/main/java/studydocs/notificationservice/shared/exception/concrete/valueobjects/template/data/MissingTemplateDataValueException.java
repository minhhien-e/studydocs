package studydocs.notificationservice.shared.exception.concrete.valueobjects.template.data;

import studydocs.notificationservice.shared.exception.abstracts.BusinessRuleViolationException;

public class MissingTemplateDataValueException extends BusinessRuleViolationException {
    public MissingTemplateDataValueException() {
        super("Giá trị của dữ liệu để tạo thông báo không được để trống (null).");
    }
}
