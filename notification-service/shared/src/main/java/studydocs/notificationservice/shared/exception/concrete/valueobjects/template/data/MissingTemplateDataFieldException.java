package studydocs.notificationservice.shared.exception.concrete.valueobjects.template.data;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingTemplateDataFieldException extends RequiredFieldMissingException {
    public MissingTemplateDataFieldException() {
        super("Nội dung thông báo");
    }
}
