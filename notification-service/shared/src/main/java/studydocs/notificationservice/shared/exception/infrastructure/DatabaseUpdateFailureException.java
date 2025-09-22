package studydocs.notificationservice.shared.exception.infrastructure;

import studydocs.notificationservice.shared.enums.InfrastructureErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.InfrastructureException;

public class DatabaseUpdateFailureException extends InfrastructureException {
    private final static String message = "%s không thành công";

    public DatabaseUpdateFailureException(String action) {
        super(String.format(message, action), InfrastructureErrorCode.DATABASE_UPDATE_FAILURE);
    }
}
