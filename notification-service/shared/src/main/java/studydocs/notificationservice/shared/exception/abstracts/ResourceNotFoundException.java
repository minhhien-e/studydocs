package studydocs.notificationservice.shared.exception.abstracts;


import studydocs.notificationservice.shared.enums.ErrorCode;

public abstract class ResourceNotFoundException extends HttpException {
    public ResourceNotFoundException(String message) {
        super(404, ErrorCode.RESOURCE_NOT_FOUND,String.format("%s không tồn tại", message));
    }
}
