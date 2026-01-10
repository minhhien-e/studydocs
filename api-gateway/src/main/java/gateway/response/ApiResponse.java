package gateway.response;

public record ApiResponse<T>(int statusCode, Integer errorCode, T data, String traceId) {
    public static <T> ApiResponse<T> success(T data, String traceId) {
        return new ApiResponse<>(200, null, data, traceId);
    }

    public static <T> ApiResponse<T> success(int statusCode, T data, String traceId) {
        return new ApiResponse<>(statusCode, null, data, traceId);

    }

    public static <T> ApiResponse<T> error(int statusCode, Integer errorCode, String traceId) {
        return new ApiResponse<>(statusCode, errorCode, null, traceId);
    }
}