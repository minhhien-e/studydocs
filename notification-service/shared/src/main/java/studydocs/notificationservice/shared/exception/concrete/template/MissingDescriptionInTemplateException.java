package studydocs.notificationservice.shared.exception.concrete.template;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingDescriptionInTemplateException extends RequiredFieldMissingException {
    public MissingDescriptionInTemplateException() {
        super("Thông tin miêu tả mẫu thông báo");
    }
}
