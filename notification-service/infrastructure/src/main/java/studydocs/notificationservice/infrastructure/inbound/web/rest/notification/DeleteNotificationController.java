package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notificationservice.application.dto.input.recipient.delete.HardDeleteNotificationInput;
import studydocs.notificationservice.application.dto.input.recipient.delete.SoftDeleteNotificationInput;
import studydocs.notificationservice.application.usecase.recipient.delete.HardDeleteNotificationUseCase;
import studydocs.notificationservice.application.usecase.recipient.delete.SoftDeleteNotificationUseCase;
import studydocs.notificationservice.infrastructure.inbound.security.helper.CurrentUserProvider;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('notification.delete')")
public class DeleteNotificationController {
    private final HardDeleteNotificationUseCase hardDeleteNotificationUseCase;
    private final SoftDeleteNotificationUseCase softDeleteNotificationUseCase;
    private final CurrentUserProvider currentUserProvider;

    @DeleteMapping("/{notificationId}/soft")
    public ResponseEntity<?> softDelete(@PathVariable("notificationId") UUID notificationId) {
        softDeleteNotificationUseCase.execute(new SoftDeleteNotificationInput(notificationId, currentUserProvider.getUserId()));
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thông báo thành công"));
    }

    @DeleteMapping("/{notificationId}/hard")
    public ResponseEntity<?> hardDelete(@PathVariable("notificationId") UUID notificationId) {
        hardDeleteNotificationUseCase.execute(new HardDeleteNotificationInput(notificationId, currentUserProvider.getUserId()));
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thông báo thành công"));
    }
}
