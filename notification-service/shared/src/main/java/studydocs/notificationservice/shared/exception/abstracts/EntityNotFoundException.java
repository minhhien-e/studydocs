package studydocs.notificationservice.shared.exception.abstracts;

import studydocs.notificationservice.shared.enums.ErrorCode;

public abstract class EntityNotFoundException extends HttpException {
    public EntityNotFoundException(String message) {
        super(404, ErrorCode.ENTITY_NOT_FOUND, String.format("%s không tồn tại", message));
    }
}
