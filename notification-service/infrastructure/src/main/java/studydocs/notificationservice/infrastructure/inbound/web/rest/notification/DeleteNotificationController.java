package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.dto.input.notification.delete.HardDeleteNotificationInput;
import studydocs.notificationservice.application.dto.input.notification.delete.SoftDeleteNotificationInput;
import studydocs.notificationservice.application.usecase.notificaton.delete.HardDeleteNotificationUseCase;
import studydocs.notificationservice.application.usecase.notificaton.delete.SoftDeleteNotificationUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class DeleteNotificationController {
    private final HardDeleteNotificationUseCase hardDeleteNotificationUseCase;
    private final SoftDeleteNotificationUseCase softDeleteNotificationUseCase;

    @DeleteMapping("/{notificationId}/soft")
    public ResponseEntity<?> softDelete(@PathVariable("notificationId") UUID notificationId, @RequestAttribute("userId") UUID requesterId) {
        softDeleteNotificationUseCase.execute(new SoftDeleteNotificationInput(notificationId, requesterId));
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thông báo thành công"));
    }

    @DeleteMapping("/{notificationId}/hard")
    public ResponseEntity<?> hardDelete(@PathVariable("notificationId") UUID notificationId, @RequestAttribute("userId") UUID requesterId) {
        hardDeleteNotificationUseCase.execute(new HardDeleteNotificationInput(notificationId, requesterId));
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thông báo thành công"));
    }
}
