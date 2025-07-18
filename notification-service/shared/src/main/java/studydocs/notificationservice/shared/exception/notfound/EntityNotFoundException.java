package studydocs.notificationservice.shared.exception.notfound;

import studydocs.notificationservice.shared.enums.ErrorCode;
import studydocs.notificationservice.shared.exception.HttpException;

public class EntityNotFoundException extends HttpException {
    public EntityNotFoundException(String message) {
        super(404, ErrorCode.ENTITY_NOT_FOUND , String.format("%s không tồn tại", message));
    }
}
