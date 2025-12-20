package studydocs.notification.api.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notification.api.dto.view.UserView;
import studydocs.notification.application.port.in.provider.CurrentTraceIdProvider;
import studydocs.notification.infrastructure.dto.ApiResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final CurrentTraceIdProvider currentTraceIdProvider;

    public UserController(CurrentTraceIdProvider currentTraceIdProvider) {
        this.currentTraceIdProvider = currentTraceIdProvider;
    }

    @GetMapping
    public ResponseEntity<?> getUser() {
        UserView user = new UserView(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"),"Hiển");
        return ResponseEntity.ok(ApiResponse.success(
                user,currentTraceIdProvider.getCurrentTraceId()
        ));
    }
}
