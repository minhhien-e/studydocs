package studydocs.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import studydocs.dto.ApiResponse;
import studydocs.response.UploadResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(FileTypeNotAllowedException.class)
    public ResponseEntity<ApiResponse<UploadResponse>> handleFileTypeNotAllowed(FileTypeNotAllowedException e) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(400, "INVALID_FILE_TYPE", e.getMessage()));
    }

    @ExceptionHandler(FileSizeExceededException.class)
    public ResponseEntity<ApiResponse<UploadResponse>> handleFileSizeExceeded(FileSizeExceededException e) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(400, "FILE_SIZE_EXCEEDED", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<UploadResponse>> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(400, "INVALID_ARGUMENT", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<UploadResponse>> handleException(Exception e) {
        log.error("Lỗi chưa được xử lý: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(500, "INTERNAL_ERROR", "Đã xảy ra lỗi: " + e.getMessage()));
    }
}