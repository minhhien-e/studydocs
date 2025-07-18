package studydocs.notificationservice.shared.exception.concrete.template.conflict;

public class TemplateAlreadyExistsException extends RuntimeException {
    public TemplateAlreadyExistsException(String name) {
        super("Mẫu thông báo với tên '" + name + "'");
    }
}
