package studydocs.notificationservice.shared.exception.concrete.valueobjects.template.subject;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingSubjectTemplateFieldException extends RequiredFieldMissingException {
    public MissingSubjectTemplateFieldException() {
        super("Tiêu đề mẫu thông báo");
    }
}
