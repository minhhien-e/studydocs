package studydocs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import studydocs.dto.response.ApiResponse;

@RestControllerAdvice
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral() {
        return ResponseEntity.internalServerError()
                .body(ApiResponse.error(500, -1));
    }
}