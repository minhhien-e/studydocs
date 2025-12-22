package studydocs.notification.api.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notification.api.dto.request.userprofile.*;
import studydocs.notification.api.helper.RequestExecutor;
import studydocs.notification.api.mapper.UserNotificationProfileMapper;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications/user-profiles")
public class UserNotificationProfileController {
    private final RequestExecutor requestExecutor;

    @GetMapping
    public ResponseEntity<?> getProfile() {
        return requestExecutor.executeWithCurrentUserAndMapView(
                (userId, request) -> UserNotificationProfileMapper.toQuery(userId),
                new Object(),
                UserNotificationProfileMapper::toView,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody CreateUserNotificationProfileRequest request) {
        return requestExecutor.executeWithCurrentUser(
                UserNotificationProfileMapper::toCommand,
                request,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/email")
    public ResponseEntity<?> updateEmail(@RequestBody UpdateEmailRequest request) {
        return requestExecutor.executeWithCurrentUser(
                UserNotificationProfileMapper::toCommand,
                request,
                HttpStatus.OK
        );
    }

    @PutMapping("/phone")
    public ResponseEntity<?> updatePhone(@RequestBody UpdatePhoneNumberRequest request) {
        return requestExecutor.executeWithCurrentUser(
                UserNotificationProfileMapper::toCommand,
                request,
                HttpStatus.OK
        );
    }

    @PostMapping("/fcm-tokens")
    public ResponseEntity<?> registerFcmToken(@RequestBody RegisterFcmTokenRequest request) {
        return requestExecutor.executeWithCurrentUser(
                UserNotificationProfileMapper::toCommand,
                request,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/fcm-tokens")
    public ResponseEntity<?> removeFcmToken(@RequestBody RemoveFcmTokenRequest request) {
        return requestExecutor.execute(
                UserNotificationProfileMapper::toCommand,
                request,
                HttpStatus.OK
        );
    }

    @PatchMapping("/preferences")
    public ResponseEntity<?> updatePreferences(@RequestBody UpdateNotificationPreferencesRequest request) {
        return requestExecutor.executeWithCurrentUser(
                UserNotificationProfileMapper::toCommand,
                request,
                HttpStatus.OK
        );
    }
}
