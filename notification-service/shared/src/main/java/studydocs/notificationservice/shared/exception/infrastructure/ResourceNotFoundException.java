package studydocs.notificationservice.shared.exception.infrastructure;

import studydocs.notificationservice.shared.enums.InfrastructureErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.InfrastructureException;

public class ResourceNotFoundException extends InfrastructureException {
    private final static String message = "%s không tồn tại";

    public ResourceNotFoundException(String type) {
        super(String.format(message, type), InfrastructureErrorCode.RESOURCE_NOT_FOUND);
    }
}
