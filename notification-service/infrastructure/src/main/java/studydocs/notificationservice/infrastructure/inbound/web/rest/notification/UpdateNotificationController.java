package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.recipient.UpdateRecipientRequestMapper;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update.MarkAllAsReadRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update.MarkAsReadRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class UpdateNotificationController {
    private final MarkAllAsReadUseCase markAllAsReadUseCase;
    private final MarkAsReadUseCase markAsReadUseCase;

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable("notificationId") UUID notificationId, @RequestAttribute("userId") UUID recipientId) {
        var request = new MarkAsReadRequest(notificationId);
        var inputModel = UpdateRecipientRequestMapper.toInput(request);
        inputModel.setRecipientId(recipientId);
        markAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }

    @PatchMapping("/read-all")
    public ResponseEntity<?> markAllAsRead(@RequestAttribute("userId") UUID recipientId) {
        var request = new MarkAllAsReadRequest();
        var inputModel = UpdateRecipientRequestMapper.toInput(request);
        inputModel.setRecipientId(recipientId);
        markAllAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }
}
