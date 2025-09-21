package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class UpdateNotificationController {
    private final MarkAllAsReadUseCase markAllAsReadUseCase;
    private final MarkAsReadUseCase markAsReadUseCase;

    @PatchMapping("/read-all")
    public ResponseEntity<?> markAllAsRead(@RequestAttribute("userId") UUID recipientId) {
        var inputModel = new MarkAllAsReadInput(recipientId);
        markAllAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu tất cả thông báo đã đọc thành công"));
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable("notificationId") UUID notificationId, @RequestAttribute("userId") UUID recipientId) {
        var inputModel = new MarkAsReadInput(notificationId, recipientId);
        markAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }
}
