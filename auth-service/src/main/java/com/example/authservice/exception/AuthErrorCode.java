package com.example.authservice.exception;

/**
 * Enum định nghĩa các mã lỗi chuẩn cho authentication service
 * Mỗi error code bao gồm:
 * - Mã lỗi duy nhất (VD: AUTH001)
 * - Message mô tả lỗi
 * 
 * Cách đặt tên mã lỗi:
 * - AUTH: Prefix cho authentication service
 * - XXX: Số thứ tự của lỗi (001-999)
 */
public enum AuthErrorCode {
    // Nhóm lỗi xác thực người dùng (001-019)
    INVALID_CREDENTIALS("AUTH001", "Thông tin đăng nhập không chính xác"),
    ACCOUNT_LOCKED("AUTH002", "Tài khoản đã bị khóa"),
    ACCOUNT_EXPIRED("AUTH003", "Tài khoản đã hết hạn"),
    INVALID_TOKEN("AUTH004", "Token không hợp lệ hoặc đã hết hạn"),
    TOKEN_EXPIRED("AUTH005", "Token đã hết hạn"),
    
    // Nhóm lỗi phân quyền (020-039)
    PERMISSION_DENIED("AUTH020", "Không có quyền truy cập tài nguyên này"),
    ROLE_NOT_FOUND("AUTH021", "Vai trò không tồn tại trong hệ thống"),
    INSUFFICIENT_PRIVILEGES("AUTH022", "Không đủ quyền để thực hiện hành động này"),
    INVALID_ROLE("AUTH023", "Vai trò không hợp lệ"),
    
    // Nhóm lỗi đăng ký/tạo tài khoản (040-059)
    EMAIL_EXISTS("AUTH040", "Email đã được sử dụng"),
    INVALID_EMAIL_FORMAT("AUTH041", "Định dạng email không hợp lệ"),
    WEAK_PASSWORD("AUTH042", "Mật khẩu không đủ mạnh"),
    PASSWORD_MISMATCH("AUTH043", "Mật khẩu xác nhận không khớp"),
    USERNAME_EXISTS("AUTH044", "Tên người dùng đã tồn tại"),
    REGISTRATION_FAILED("AUTH045", "Đăng ký tài khoản thất bại"),
    
    // Nhóm lỗi xác thực email (060-079)
    EMAIL_VERIFICATION_EXPIRED("AUTH060", "Mã xác thực email đã hết hạn"),
    EMAIL_ALREADY_VERIFIED("AUTH061", "Email đã được xác thực"),
    EMAIL_VERIFICATION_FAILED("AUTH062", "Xác thực email thất bại"),
    
    // Nhóm lỗi social login (080-099)
    SOCIAL_LOGIN_FAILED("AUTH080", "Đăng nhập bằng tài khoản social thất bại"),
    SOCIAL_EMAIL_NOT_VERIFIED("AUTH081", "Email từ tài khoản social chưa được xác thực"),
    SOCIAL_ACCOUNT_NOT_LINKED("AUTH082", "Tài khoản social chưa được liên kết"),
    
    // Nhóm lỗi refresh token (100-119)
    REFRESH_TOKEN_NOT_FOUND("AUTH100", "Refresh token không tồn tại"),
    REFRESH_TOKEN_EXPIRED("AUTH101", "Refresh token đã hết hạn"),
    REFRESH_TOKEN_INVALID("AUTH102", "Refresh token không hợp lệ"),
    
    // Nhóm lỗi chung (900-999)
    INVALID_REQUEST("AUTH900", "Yêu cầu không hợp lệ"),
    INTERNAL_ERROR("AUTH999", "Lỗi hệ thống, vui lòng thử lại sau");

    private final String code;
    private final String message;

    AuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
} 