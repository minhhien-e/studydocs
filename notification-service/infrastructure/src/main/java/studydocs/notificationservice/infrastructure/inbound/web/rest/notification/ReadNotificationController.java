package studydocs.notificationservice.infrastructure.inbound.web.rest.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.application.usecase.notificaton.read.GetNotificationByRecipientIdUseCase;
import studydocs.notificationservice.application.usecase.recipient.read.CountUnreadUseCase;
import studydocs.notificationservice.infrastructure.inbound.web.dto.mapper.recipient.ReadRecipientRequestMapper;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read.CountUnreadRequest;
import studydocs.notificationservice.infrastructure.inbound.web.dto.request.recipient.read.GetNotificationByRecipientIdRequest;
import studydocs.notificationservice.shared.api.ApiResponse;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class ReadNotificationController {
    private final GetNotificationByRecipientIdUseCase getNotificationByRecipientIdUseCase;
    private final CountUnreadUseCase countUnreadUseCase;

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<?> getByRecipientId(@PathVariable("recipientId") UUID recipientId,
                                              @RequestParam(value = "createAt", required = false) Optional<LocalDateTime> createAt,
                                              @RequestParam(value = "limit", required = false) int limit) {
        var request = new GetNotificationByRecipientIdRequest(recipientId, createAt, limit);
        var outputModel = getNotificationByRecipientIdUseCase.execute(ReadRecipientRequestMapper.toInput(request));
        return ResponseEntity.ok(ApiResponse.success(outputModel, null));
    }

    @GetMapping("/recipient/{recipientId}/count-unread")
    public ResponseEntity<?> countUnread(@PathVariable("recipientId") UUID recipientId, @RequestAttribute("userId") UUID requesterId) {
        var request = new CountUnreadRequest(recipientId);
        var inputModel = ReadRecipientRequestMapper.toInput(request);
        inputModel.setRequesterId(requesterId);
        var count = countUnreadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(count, null));
    }
}
