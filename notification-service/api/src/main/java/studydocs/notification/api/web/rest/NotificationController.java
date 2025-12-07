package studydocs.notification.api.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notification.api.dto.request.notification.*;
import studydocs.notification.api.helper.RequestExecutor;
import studydocs.notification.api.mapper.NotificationMapper;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final RequestExecutor requestExecutor;

    /// Create
    @PostMapping
    public ResponseEntity<?> addNotification(@RequestBody AddNotificationRequest request) {
        return requestExecutor.executeWithCurrentUser(NotificationMapper::toCommand, request, HttpStatus.OK);
    }

    @PostMapping("/{notificationId}/receive")
    public ResponseEntity<?> receiveNotification(@PathVariable UUID notificationId, @RequestBody ReceiveNotificationRequest request) {
        request.setNotificationId(notificationId);
        return requestExecutor.executeWithCurrentUser(NotificationMapper::toCommand, request, HttpStatus.OK);
    }

    /// Read
    @GetMapping
    public ResponseEntity<?> getByRecipientId(GetNotificationByRecipientIdRequest request) {
        return requestExecutor.executeWithCurrentUser(NotificationMapper::toQuery, request, HttpStatus.OK);
    }

    @GetMapping("/count-unread")
    public ResponseEntity<?> countUnread() {
        var request = new CountUnreadNotificationRequest();
        return requestExecutor.executeWithCurrentUser(NotificationMapper::toQuery, request, HttpStatus.OK);
    }

    /// Update
    @PatchMapping("/read-all")
    public ResponseEntity<?> markAllAsRead() {
        var request = new MarkAllAsReadRequest();
        return requestExecutor.executeWithCurrentUser(NotificationMapper::toCommand, request, HttpStatus.OK);
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable("notificationId") UUID notificationId) {
        var request = new MarkAsReadRequest(notificationId);
        return requestExecutor.executeWithCurrentUser(NotificationMapper::toCommand, request, HttpStatus.OK);
    }

    @PatchMapping("/restore")
    public ResponseEntity<?> restore(@RequestBody RestoreNotificationsRequest request) {
        return requestExecutor.executeWithCurrentUser(NotificationMapper::toCommand, request, HttpStatus.OK);
    }

    /// Delete
    @DeleteMapping("/{notificationId}/soft")
    public ResponseEntity<?> softDelete(@PathVariable("notificationId") UUID notificationId) {
        var request = new SoftDeleteNotificationRequest(notificationId);
        return requestExecutor.executeWithCurrentUser(NotificationMapper::toCommand, request, HttpStatus.OK);

    }

    @DeleteMapping("/{notificationId}/hard")
    public ResponseEntity<?> hardDelete(@PathVariable("notificationId") UUID notificationId) {
        var request = new HardDeleteNotificationRequest(notificationId);
        return requestExecutor.executeWithCurrentUser(NotificationMapper::toCommand, request, HttpStatus.OK);
    }

}
