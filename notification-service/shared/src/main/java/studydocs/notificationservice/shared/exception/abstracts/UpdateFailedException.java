package studydocs.notificationservice.shared.exception.abstracts;

import studydocs.notificationservice.shared.enums.ErrorCode;

public class UpdateFailedException extends HttpException {
    public UpdateFailedException() {
        super(500, ErrorCode.UPDATE_FAILED, "Thay đổi không thành công");
    }
}
