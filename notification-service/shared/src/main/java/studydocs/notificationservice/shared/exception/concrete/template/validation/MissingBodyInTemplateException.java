package studydocs.notificationservice.shared.exception.concrete.template.validation;

public class MissingBodyInTemplateException extends RuntimeException {
    public MissingBodyInTemplateException() {
        super("Nội dung mẫu thông báo");
    }
}
