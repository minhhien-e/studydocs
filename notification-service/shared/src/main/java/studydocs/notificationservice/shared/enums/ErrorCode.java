package studydocs.notificationservice.shared.enums;

public enum ErrorCode {
    // VALIDATION
    REQUIRED_FIELD_MISSING,    // Thiếu trường bắt buộc
    INVALID_FORMAT,                     // Sai định dạng (email, số, date,...)
    INVALID_VALUE,

    //  BUSINESS RULE
    BUSINESS_RULE_VIOLATION,    // Vi phạm quy tắc nghiệp vụ
    FUTURE_DATE_NOT_ALLOWED,

    // CONFLICT / DUPLICATE
    DUPLICATE_ENTRY,                   // Dữ liệu bị trùng (email, username...)
    RESOURCE_ALREADY_EXISTS,     // Tài nguyên đã tồn tại

    //  DATABASE / finalRAINT
    CONSTRAINT_VIOLATION,          // Vi phạm ràng buộc DB

    //  AUTHORIZATION
    UNAUTHORIZED,                         // Chưa đăng nhập
    FORBIDDEN,                              // Không có quyền truy cập

    //  NOT FOUND
    RESOURCE_NOT_FOUND,             // Không tìm thấy tài nguyên
    ENTITY_NOT_FOUND,                 // Không tìm thấy thực thể
    // INTERNAL
    INTERNAL_SERVER_ERROR,         // Lỗi không xác định

    // CUSTOM
    TOO_MANY_REQUESTS,
}
