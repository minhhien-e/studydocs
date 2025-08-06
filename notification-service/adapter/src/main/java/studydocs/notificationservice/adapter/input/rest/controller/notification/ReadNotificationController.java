package studydocs.notificationservice.adapter.input.rest.controller.notification;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studydocs.notificationservice.adapter.input.rest.request.recipient.read.CountUnreadRequest;
import studydocs.notificationservice.adapter.input.rest.request.recipient.read.GetNotificationByRecipientIdRequest;
import studydocs.notificationservice.application.port.input.usecase.notificaton.read.GetNotificationByRecipientIdUseCase;
import studydocs.notificationservice.application.port.input.usecase.recipient.read.CountUnreadUseCase;
import studydocs.notificationservice.shared.api.ApiResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class ReadNotificationController {
    private final GetNotificationByRecipientIdUseCase getNotificationByRecipientIdUseCase;
    private final CountUnreadUseCase countUnreadUseCase;

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<?> getByRecipientId(@Valid @ModelAttribute GetNotificationByRecipientIdRequest request) {
        var outputModel = getNotificationByRecipientIdUseCase.execute(request.toInputModel());
        return ResponseEntity.ok(ApiResponse.success(outputModel, null));
    }

    @GetMapping("/recipient/{recipientId}/count-unread")
    public ResponseEntity<?> countUnread(@PathVariable("recipientId") UUID recipientId, @RequestParam("userId") UUID requesterId) {
        var request = new CountUnreadRequest(recipientId);
        var inputModel = request.toInputModel();
        inputModel.setRequesterId(requesterId);
        var count = countUnreadUseCase.execute(inputModel);
        return ResponseEntity.ok(ApiResponse.success(count, null));
    }
}
