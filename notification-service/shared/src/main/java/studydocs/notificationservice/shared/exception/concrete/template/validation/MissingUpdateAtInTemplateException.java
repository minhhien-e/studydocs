package studydocs.notificationservice.shared.exception.concrete.template.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingUpdateAtInTemplateException extends RequiredFieldMissingException {
    public MissingUpdateAtInTemplateException() {
        super("Thời gian thay đổi mẫu thông báo");
    }
}
