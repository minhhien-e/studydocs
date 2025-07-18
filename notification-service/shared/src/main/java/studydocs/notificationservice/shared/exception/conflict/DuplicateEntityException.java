package studydocs.notificationservice.shared.exception.conflict;


import studydocs.notificationservice.shared.enums.ErrorCode;
import studydocs.notificationservice.shared.exception.HttpException;

public class DuplicateEntityException extends HttpException {
    public DuplicateEntityException(String message) {
        super(409, ErrorCode.DUPLICATE_ENTRY, String.format("%s đã tồn tại.", message));
    }
}
