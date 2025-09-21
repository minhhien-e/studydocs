package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.dto.input.notification.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.application.dto.input.recipient.create.CountUnreadInput;
import studydocs.notificationservice.application.usecase.notificaton.read.GetNotificationByRecipientIdUseCase;
import studydocs.notificationservice.application.usecase.recipient.read.CountUnreadUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.NotificationResponse;
import studydocs.notificationservice.shared.paging.SliceInput;
import studydocs.notificationservice.shared.paging.SliceOutput;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class ReadNotificationController {
    private final GetNotificationByRecipientIdUseCase getNotificationByRecipientIdUseCase;
    private final CountUnreadUseCase countUnreadUseCase;

    @GetMapping
    public ResponseEntity<?> getByRecipientId(@RequestAttribute("userId") UUID recipientId, @RequestParam boolean isDeleted, @RequestParam(value = "createAt") Optional<LocalDateTime> createAt, @RequestParam(value = "limit", required = false) int limit) {
        if (createAt.isEmpty()) createAt = Optional.of(LocalDateTime.now());
        var outputModel = getNotificationByRecipientIdUseCase.execute(new SliceInput<>(new GetNotificationByRecipientIdInput(recipientId, isDeleted, createAt.get()), limit));
        var responses = new SliceOutput<>(outputModel.content().stream().map(NotificationResponse::toResponse).toList(), outputModel.hasNext());
        return ResponseEntity.ok(ApiResponse.success(responses, "Lấy danh sách thông báo thành công"));
    }


    @GetMapping("/recipient/count-unread")
    public ResponseEntity<?> countUnread(@RequestAttribute("userId") UUID recipientId) {
        var inputModel = new CountUnreadInput(recipientId);
        var count = countUnreadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(count, "Đếm số thông báo chưa đọc thành công"));
    }
}
