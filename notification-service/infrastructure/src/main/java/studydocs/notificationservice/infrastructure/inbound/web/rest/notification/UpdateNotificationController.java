package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;
import studydocs.notificationservice.application.dto.input.recipient.update.RestoreNotificationsInput;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.application.usecase.recipient.update.RestoreNotificationsUseCase;
import studydocs.notificationservice.infrastructure.inbound.security.helper.CurrentUserProvider;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class UpdateNotificationController {
    private final MarkAllAsReadUseCase markAllAsReadUseCase;
    private final MarkAsReadUseCase markAsReadUseCase;
    private final CurrentUserProvider currentUserProvider;
    private final RestoreNotificationsUseCase restoreNotificationsUseCase;

    @PreAuthorize("hasAuthority('notification.mark.read')")
    @PatchMapping("/read-all")
    public ResponseEntity<?> markAllAsRead() {
        var inputModel = new MarkAllAsReadInput(currentUserProvider.getUserId());
        markAllAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu tất cả thông báo đã đọc thành công"));
    }

    @PreAuthorize("hasAuthority('notification.mark.read')")
    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable("notificationId") UUID notificationId) {
        var inputModel = new MarkAsReadInput(notificationId, currentUserProvider.getUserId());
        markAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }

    @PreAuthorize("hasAuthority('notification.restore')")
    @PatchMapping("/restore")
    public ResponseEntity<?> restore(@RequestBody List<UUID> notificationIds) {
        var inputModel = new RestoreNotificationsInput(notificationIds, currentUserProvider.getUserId());
        restoreNotificationsUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Khôi phục thông báo thành công"));
    }
}
