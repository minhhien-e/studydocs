package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAllAsReadInput;
import studydocs.notificationservice.application.dto.input.recipient.update.MarkAsReadInput;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.application.usecase.recipient.update.MarkAsReadUseCase;
import studydocs.notificationservice.infrastructure.inbound.security.helper.CurrentUserProvider;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class UpdateNotificationController {
    private final MarkAllAsReadUseCase markAllAsReadUseCase;
    private final MarkAsReadUseCase markAsReadUseCase;
    private final CurrentUserProvider currentUserProvider;


    @PatchMapping("/read-all")
    public ResponseEntity<?> markAllAsRead() {
        var inputModel = new MarkAllAsReadInput(currentUserProvider.getUserId());
        markAllAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu tất cả thông báo đã đọc thành công"));
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable("notificationId") UUID notificationId) {
        var inputModel = new MarkAsReadInput(notificationId, currentUserProvider.getUserId());
        markAsReadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(null, "Đánh dấu đã đọc thành công"));
    }
}
