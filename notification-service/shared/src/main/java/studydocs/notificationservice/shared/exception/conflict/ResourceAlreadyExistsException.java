package studydocs.notificationservice.shared.exception.conflict;


import studydocs.notificationservice.shared.enums.ErrorCode;
import studydocs.notificationservice.shared.exception.HttpException;

public class ResourceAlreadyExistsException extends HttpException {
    public ResourceAlreadyExistsException(String message) {
        super(409, ErrorCode.RESOURCE_ALREADY_EXISTS, String.format("%s đã tồn tại.", message));
    }
}
