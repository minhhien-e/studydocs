package studydocs.notificationservice.adapter.input.rest.controller.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notificationservice.adapter.input.rest.request.recipient.update.MarkAllAsReadRequest;
import studydocs.notificationservice.adapter.input.rest.request.recipient.update.MarkAsReadRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class UpdateNotificationController {
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable("notificationId") UUID notificationId) {
        var request = new MarkAsReadRequest(notificationId);
        var inputModel = request.toInputModel();
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }
    @PutMapping("/read-all")
    public ResponseEntity<?> markAllAsRead() {
        var request = new MarkAllAsReadRequest();
        var inputModel = request.toInputModel();
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }
}
