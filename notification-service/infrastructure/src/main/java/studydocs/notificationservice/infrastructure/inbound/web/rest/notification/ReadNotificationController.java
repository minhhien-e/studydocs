package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notificationservice.application.dto.input.notification.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.application.dto.input.recipient.create.CountUnreadInput;
import studydocs.notificationservice.application.usecase.notificaton.read.GetNotificationByRecipientIdUseCase;
import studydocs.notificationservice.application.usecase.recipient.read.CountUnreadUseCase;
import studydocs.notificationservice.infrastructure.inbound.security.helper.CurrentUserProvider;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.ApiResponse;
import studydocs.notificationservice.infrastructure.inbound.web.dto.response.NotificationResponse;
import studydocs.notificationservice.shared.paging.SliceInput;
import studydocs.notificationservice.shared.paging.SliceOutput;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class ReadNotificationController {
    private final GetNotificationByRecipientIdUseCase getNotificationByRecipientIdUseCase;
    private final CountUnreadUseCase countUnreadUseCase;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping
    public ResponseEntity<?> getByRecipientId(@RequestParam boolean isDeleted, @RequestParam(value = "createAt") Optional<LocalDateTime> createAt, @RequestParam(value = "limit", required = false) int limit) {
        if (createAt.isEmpty()) createAt = Optional.of(LocalDateTime.now());
        var outputModel = getNotificationByRecipientIdUseCase.execute(new SliceInput<>(new GetNotificationByRecipientIdInput(currentUserProvider.getUserId(), isDeleted, createAt.get()), limit));
        var responses = new SliceOutput<>(outputModel.content().stream().map(NotificationResponse::toResponse).toList(), outputModel.hasNext());
        return ResponseEntity.ok(ApiResponse.success(responses, "Lấy danh sách thông báo thành công"));
    }


    @GetMapping("/recipient/count-unread")
    public ResponseEntity<?> countUnread() {
        var inputModel = new CountUnreadInput(currentUserProvider.getUserId());
        var count = countUnreadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(count, "Đếm số thông báo chưa đọc thành công"));
    }
}
