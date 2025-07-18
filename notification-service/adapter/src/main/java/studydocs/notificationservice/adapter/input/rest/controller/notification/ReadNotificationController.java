package studydocs.notificationservice.adapter.input.rest.controller.notification;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studydocs.notificationservice.adapter.input.rest.request.template.read.GetNotificationByRecipientIdRequest;
import studydocs.notificationservice.application.port.input.usecase.notificaton.read.GetNotificationByRecipientIdUseCase;
import studydocs.notificationservice.shared.api.ApiResponse;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class ReadNotificationController {
    private final GetNotificationByRecipientIdUseCase getNotificationByRecipientIdUseCase;

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<?> getByRecipientId(@Valid @ModelAttribute GetNotificationByRecipientIdRequest request) {
        var outputModel = getNotificationByRecipientIdUseCase.execute(request.toInputModel());
        return ResponseEntity.ok(ApiResponse.success(outputModel, null));
    }
}
