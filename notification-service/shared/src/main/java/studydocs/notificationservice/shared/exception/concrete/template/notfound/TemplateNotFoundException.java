package studydocs.notificationservice.shared.exception.concrete.template.notfound;

import studydocs.notificationservice.shared.exception.notfound.ResourceNotFoundException;

import java.util.UUID;

public class TemplateNotFoundException extends ResourceNotFoundException {
    public TemplateNotFoundException(UUID id) {
        super("Mẫu thông báo với ID '" + id + "'");
    }
    public TemplateNotFoundException(String name) {
        super("Mẫu thông báo với tên '" + name + "'");
    }
}
