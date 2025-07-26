package studydocs.notificationservice.shared.exception.abstracts;


import studydocs.notificationservice.shared.enums.ErrorCode;

public abstract class ResourceAlreadyExistsException extends HttpException {
    public ResourceAlreadyExistsException(String message) {
        super(409, ErrorCode.RESOURCE_ALREADY_EXISTS, String.format("%s đã tồn tại.", message));
    }
}
