package studydocs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import studydocs.dto.response.ApiResponse;

@RestControllerAdvice
@lombok.extern.slf4j.Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleDocumentNotFound(DocumentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(404, ex.getErrorCode()));
    }

    @ExceptionHandler(DocumentProcessingException.class)
    public ResponseEntity<ApiResponse<String>> handleDocumentProcessing(DocumentProcessingException ex) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(400, ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(DocumentValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(DocumentValidationException ex) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(400, ex.getErrorCode()));
    }

    @ExceptionHandler(RemoteUploadException.class)
    public ResponseEntity<ApiResponse<Void>> handleRemoteUpload(RemoteUploadException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(ApiResponse.error(502, ex.getErrorCode()));
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDenied(
            org.springframework.security.access.AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(403, 505)); // 505 - Access Denied
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneral(Exception ex) {
        log.error("Internal Server Error: ", ex);
        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(500, -1,
                        "Internal Error: " + ex.getClass().getSimpleName() + ": " + ex.getMessage()));
    }
}