package studydocs.notificationservice.shared.exception.concrete.template.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingChanelInTemplateException extends RequiredFieldMissingException {
    public MissingChanelInTemplateException() {
        super("Loại kênh trong mẫu thông báo");
    }
}
