package studydocs.notificationservice.shared.exception.concrete.valueobjects.template.body;

public class MissingBodyTemplateFieldException extends RuntimeException {
    public MissingBodyTemplateFieldException() {
        super("Nội dung mẫu thông báo");
    }
}
