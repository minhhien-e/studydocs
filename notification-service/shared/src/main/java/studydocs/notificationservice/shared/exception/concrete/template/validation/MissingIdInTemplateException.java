package studydocs.notificationservice.shared.exception.concrete.template.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingIdInTemplateException extends RequiredFieldMissingException {
    public MissingIdInTemplateException() {
        super("ID mẫu thông báo");
    }
}
