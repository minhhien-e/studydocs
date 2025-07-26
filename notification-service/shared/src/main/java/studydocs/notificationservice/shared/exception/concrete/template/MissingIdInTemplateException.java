package studydocs.notificationservice.shared.exception.concrete.template;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingIdInTemplateException extends RequiredFieldMissingException {
    public MissingIdInTemplateException() {
        super("ID mẫu thông báo");
    }
}
