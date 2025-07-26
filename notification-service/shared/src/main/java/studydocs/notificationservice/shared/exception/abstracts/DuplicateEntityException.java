package studydocs.notificationservice.shared.exception.abstracts;


import studydocs.notificationservice.shared.enums.ErrorCode;

public abstract class DuplicateEntityException extends HttpException {
    public DuplicateEntityException(String message) {
        super(409, ErrorCode.DUPLICATE_ENTRY, String.format("%s đã tồn tại.", message));
    }
}
