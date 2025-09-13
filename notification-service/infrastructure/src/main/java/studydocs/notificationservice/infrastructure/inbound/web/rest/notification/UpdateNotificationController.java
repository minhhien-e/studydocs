package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.ErrorResponse;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.StandardApiResponses;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.SuccessfulResponse;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update.MarkAllAsReadRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.update.MarkAsReadRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import java.util.UUID;

import static studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.RecipientRequestMapper.toInput;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Thông báo", description = "API quản lý thông báo - xem, đếm và quản lý trạng thái thông báo")
public class UpdateNotificationController {
    private final MarkAllAsReadUseCase markAllAsReadUseCase;
    private final MarkAsReadUseCase markAsReadUseCase;

    @Operation(summary = "Đánh dấu tất cả thông báo đã đọc", description = "Đánh dấu tất cả thông báo của người dùng hiện tại là đã đọc")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Đánh dấu tất cả thông báo đã đọc thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "404", code = "USER_NOT_FOUND", message = "Không tìm thấy người dùng với ID đã cho"),
                    @ErrorResponse(statusCode = "500", code = "UPDATE_FAILED", message = "Cập nhật thông tin không thành công")

            }
    )
    @PatchMapping("/read-all")
    public ResponseEntity<?> markAllAsRead(@RequestAttribute("userId") UUID recipientId) {
        var request = new MarkAllAsReadRequest();
        var inputModel = toInput(request);
        inputModel.setRecipientId(recipientId);
        markAllAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu tất cả thông báo đã đọc thành công"));
    }

    @Operation(summary = "Đánh dấu thông báo đã đọc", description = "Đánh dấu một thông báo cụ thể là đã đọc")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Đánh dấu đã đọc thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "404", code = "RESOURCE_NOT_FOUND", message = "Không tìm thấy thông báo với ID đã cho"),
                    @ErrorResponse(statusCode = "500", code = "UPDATE_FAILED", message = "Cập nhật thông tin không thành công")
            }
    )
    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable("notificationId") UUID notificationId, @RequestAttribute("userId") UUID recipientId) {
        var request = new MarkAsReadRequest(notificationId);
        var inputModel = toInput(request);
        inputModel.setRecipientId(recipientId);
        markAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }
}
