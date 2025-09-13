package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.notificaton.read.GetNotificationByRecipientIdUseCase;
import studydocs.notificationservice.application.usecase.recipient.read.CountUnreadUseCase;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.ErrorResponse;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.StandardApiResponses;
import studydocs.notificationservice.infrastructure.inbound.swagger.annotation.SuccessfulResponse;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read.CountUnreadRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read.GetNotificationByRecipientIdRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.RecipientRequestMapper.toInput;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Thông báo", description = "API quản lý thông báo - xem, đếm và quản lý trạng thái thông báo")
public class ReadNotificationController {
    private final GetNotificationByRecipientIdUseCase getNotificationByRecipientIdUseCase;
    private final CountUnreadUseCase countUnreadUseCase;

    @Operation(summary = "Lấy danh sách thông báo theo người nhận", description = "Lấy danh sách thông báo của một người nhận cụ thể với khả năng lọc theo thời gian tạo và giới hạn số lượng")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Lấy danh sách thông báo thành công", data = Object.class),
            errorExamples = {
                    @ErrorResponse(statusCode = "404", code = "RESOURCE_NOT_FOUND", message = "Không tìm thấy người nhận với ID đã cho"),
            }
    )
    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<?> getByRecipientId(@PathVariable("recipientId") UUID recipientId,
                                              @RequestParam(value = "createAt", required = false) Optional<LocalDateTime> createAt,
                                              @RequestParam(value = "limit", required = false) int limit) {
        var request = new GetNotificationByRecipientIdRequest(recipientId, createAt, limit);
        var outputModel = getNotificationByRecipientIdUseCase.execute(toInput(request));
        return ResponseEntity.ok(ApiResponse.success(outputModel, "Lấy danh sách thông báo thành công"));
    }

    @Operation(summary = "Đếm số thông báo chưa đọc", description = "Đếm số lượng thông báo chưa đọc của một người nhận cụ thể")
    @StandardApiResponses(
            successExample = @SuccessfulResponse(message = "Đếm số thông báo chưa đọc thành công", data = Integer.class)
    )
    @GetMapping("/recipient/count-unread")
    public ResponseEntity<?> countUnread(@RequestAttribute("userId") UUID recipientId) {
        var request = new CountUnreadRequest(recipientId);
        var inputModel = toInput(request);
        var count = countUnreadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(count, "Đếm số thông báo chưa đọc thành công"));
    }
}
