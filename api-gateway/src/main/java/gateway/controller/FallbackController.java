package gateway.controller;

import gateway.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FallbackController {

    private <T> ResponseEntity<ApiResponse<T>> fallbackResponse(String serviceName) {
        ApiResponse<T> response = ApiResponse.error(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                null,
                serviceName + " Service is currently unavailable. Please try again later."
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/fallback/users")
    public ResponseEntity<ApiResponse<Void>> userFallback() {
        return fallbackResponse("User");
    }

    @GetMapping("/fallback/authentica")
    public ResponseEntity<ApiResponse<Void>> authenticaFallback() {
        return fallbackResponse("Authentica");
    }

    @GetMapping("/fallback/notification")
    public ResponseEntity<ApiResponse<Void>> notificationFallback() {
        return fallbackResponse("Notification");
    }

    @GetMapping("/fallback/upload")
    public ResponseEntity<ApiResponse<Void>> uploadFallback() {
        return fallbackResponse("Upload");
    }

    @GetMapping("/fallback/review")
    public ResponseEntity<ApiResponse<Void>> reviewFallback() {
        return fallbackResponse("Review");
    }

    @GetMapping("/fallback/follow")
    public ResponseEntity<ApiResponse<Void>> followFallback() {
        return fallbackResponse("Follow");
    }

    @GetMapping("/fallback/academic")
    public ResponseEntity<ApiResponse<Void>> academicFallback() {
        return fallbackResponse("Academic");
    }

    @GetMapping("/fallback/search")
    public ResponseEntity<ApiResponse<Void>> searchFallback() {
        return fallbackResponse("Search");
    }

    @GetMapping("/fallback/document")
    public ResponseEntity<ApiResponse<Void>> documentFallback() {
        return fallbackResponse("Document");
    }

    @GetMapping("/fallback/media")
    public ResponseEntity<ApiResponse<Void>> mediaFallback() {
        return fallbackResponse("Media");
    }
}
