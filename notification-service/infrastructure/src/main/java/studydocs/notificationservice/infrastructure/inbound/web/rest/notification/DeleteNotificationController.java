package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.notificaton.delete.HardDeleteNotificationUseCase;
import studydocs.notificationservice.application.usecase.notificaton.delete.SoftDeleteNotificationUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.notification.delete.HardDeleteNotificationRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.notification.delete.SoftDeleteNotificationRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import java.util.UUID;

import static studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.NotificationRequestMapper.toInput;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class DeleteNotificationController {
    private final HardDeleteNotificationUseCase hardDeleteNotificationUseCase;
    private final SoftDeleteNotificationUseCase softDeleteNotificationUseCase;

    @DeleteMapping("/{notificationId}/soft")
    public ResponseEntity<?> softDelete(@PathVariable("notificationId") UUID notificationId, @RequestAttribute("userId") UUID requesterId) {
        var request = new SoftDeleteNotificationRequest(notificationId);
        softDeleteNotificationUseCase.execute(toInput(request, requesterId));
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thông báo thành công"));
    }

    @DeleteMapping("/{notificationId}/hard")
    public ResponseEntity<?> hardDelete(@PathVariable("notificationId") UUID notificationId, @RequestAttribute("userId") UUID requesterId) {
        var request = new HardDeleteNotificationRequest(notificationId);
        hardDeleteNotificationUseCase.execute(toInput(request, requesterId));
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thông báo thành công"));
    }
}
