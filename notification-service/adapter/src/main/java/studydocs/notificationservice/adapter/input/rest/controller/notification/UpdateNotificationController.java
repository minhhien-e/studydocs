package studydocs.notificationservice.adapter.input.rest.controller.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.adapter.input.rest.request.recipient.update.MarkAllAsReadRequest;
import studydocs.notificationservice.adapter.input.rest.request.recipient.update.MarkAsReadRequest;
import studydocs.notificationservice.application.port.input.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.application.port.input.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.shared.api.ApiResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class UpdateNotificationController {
    private final MarkAllAsReadUseCase markAllAsReadUseCase;
    private final MarkAsReadUseCase markAsReadUseCase;
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable("notificationId") UUID notificationId, @RequestParam("userId") UUID recipientId) {
        var request = new MarkAsReadRequest(notificationId);
        request.setRecipientId(recipientId);
        var inputModel = request.toInputModel();
        markAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }
    @PutMapping("/read-all")
    public ResponseEntity<?> markAllAsRead(@RequestParam("userId") UUID recipientId) {
        var request = new MarkAllAsReadRequest();
        request.setRecipientId(recipientId);
        var inputModel = request.toInputModel();
        markAllAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }
}
