package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.notificaton.delete.HardDeleteNotificationUseCase;
import studydocs.notificationservice.application.usecase.notificaton.delete.SoftDeleteNotificationUseCase;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.ErrorResponse;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.StandardApiResponses;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.SuccessfulResponse;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.notification.delete.HardDeleteNotificationRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.notification.delete.SoftDeleteNotificationRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import java.util.UUID;

import static studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.NotificationRequestMapper.toInput;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Thông báo", description = "API quản lý thông báo - xem, đếm và quản lý trạng thái thông báo")
public class DeleteNotificationController {
    private final HardDeleteNotificationUseCase hardDeleteNotificationUseCase;
    private final SoftDeleteNotificationUseCase softDeleteNotificationUseCase;

    @Operation(summary = "Xóa mềm thông báo", description = "Xóa mềm một thông báo cụ thể (đánh dấu là đã xóa nhưng vẫn lưu trong database)")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Xóa thông báo thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "404", code = "RESOURCE_NOT_FOUND", message = "Không tìm thấy thông báo với ID đã cho"),
                    @ErrorResponse(statusCode = "500", code = "UPDATE_FAILED", message = "Xóa thông báo không thành công")
            }
    )
    @DeleteMapping("/{notificationId}/soft")
    public ResponseEntity<?> softDelete(@PathVariable("notificationId") UUID notificationId, @RequestAttribute("userId") UUID requesterId) {
        var request = new SoftDeleteNotificationRequest(notificationId);
        softDeleteNotificationUseCase.execute(toInput(request, requesterId));
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thông báo thành công"));
    }

    @Operation(summary = "Xóa cứng thông báo", description = "Xóa cứng một thông báo cụ thể (xóa vĩnh viễn khỏi database)")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Xóa thông báo thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "404", code = "RESOURCE_NOT_FOUND", message = "Không tìm thấy thông báo với ID đã cho"),
                    @ErrorResponse(statusCode = "500", code = "UPDATE_FAILED", message = "Xóa thông báo không thành công")
            }
    )
    @DeleteMapping("/{notificationId}/hard")
    public ResponseEntity<?> hardDelete(@PathVariable("notificationId") UUID notificationId, @RequestAttribute("userId") UUID requesterId) {
        var request = new HardDeleteNotificationRequest(notificationId);
        hardDeleteNotificationUseCase.execute(toInput(request, requesterId));
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thông báo thành công"));
    }
}
