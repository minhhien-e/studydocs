package studydocs.notificationservice.shared.exception.concrete.template.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingNameInTemplateException extends RequiredFieldMissingException {
    public MissingNameInTemplateException() {
        super("Tên mẫu thông báo");
    }
}
