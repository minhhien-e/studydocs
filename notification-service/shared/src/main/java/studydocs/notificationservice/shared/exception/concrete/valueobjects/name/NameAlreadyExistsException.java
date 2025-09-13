package studydocs.notificationservice.shared.exception.concrete.valueobjects.name;

import studydocs.notificationservice.shared.exception.abstracts.ResourceAlreadyExistsException;

public class NameAlreadyExistsException extends ResourceAlreadyExistsException {
    public NameAlreadyExistsException(String type) {
        super("Tên " + type);
    }
}
