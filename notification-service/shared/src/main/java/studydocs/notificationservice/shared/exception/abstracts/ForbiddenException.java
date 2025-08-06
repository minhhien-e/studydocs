package studydocs.notificationservice.shared.exception.abstracts;

import studydocs.notificationservice.shared.enums.ErrorCode;

public class ForbiddenException extends HttpException {
    public ForbiddenException() {
        super(403, ErrorCode.FORBIDDEN, "Không có quyền truy cập tài nguyên");
    }
}
