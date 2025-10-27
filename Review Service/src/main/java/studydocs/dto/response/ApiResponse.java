package studydocs.dto.response;

public record ApiResponse<T> (
        int statusCode,
        int errorCode,
        T data
) {
    public static <T> ApiResponse<T> success(int statusCode, T data) {
        return new ApiResponse<>(statusCode, 0, data);  // 0 = success
    }

    public static <T> ApiResponse<T> error(int statusCode, int errorCode, T data) {
        return new ApiResponse<>(statusCode, errorCode, data);
    }

    public static <T> ApiResponse<T> error(int statusCode, int errorCode) {
        return new ApiResponse<>(statusCode, errorCode, null);
    }
}