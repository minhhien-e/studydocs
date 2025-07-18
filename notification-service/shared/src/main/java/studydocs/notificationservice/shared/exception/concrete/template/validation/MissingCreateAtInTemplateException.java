package studydocs.notificationservice.shared.exception.concrete.template.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingCreateAtInTemplateException extends RequiredFieldMissingException {
    public MissingCreateAtInTemplateException() {
        super("Thời gian tạo mẫu thông báo");
    }
}
