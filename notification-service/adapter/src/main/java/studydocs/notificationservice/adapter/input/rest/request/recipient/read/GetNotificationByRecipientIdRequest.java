package studydocs.notificationservice.adapter.input.rest.request.recipient.read;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import studydocs.notificationservice.application.port.input.dto.inputmodel.notification.read.GetNotificationByRecipientIdInputModel;
import studydocs.notificationservice.application.port.input.dto.paging.SliceInput;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record GetNotificationByRecipientIdRequest(
        @NotNull(message = "Id của người nhận không được bỏ trống")
        UUID recipientId,
        Optional<LocalDateTime> createdAt,
        @Min(value = 1, message = "Số trang phải lớn hơn 0")
        int pageNumber,
        @Max(value = 50, message = "Chỉ có lấy lấy tối đa 50")
        @Min(value = 1, message = "Số lượng phải lớn 0")
        int limit) {
    public SliceInput<GetNotificationByRecipientIdInputModel> toInputModel() {
        var request = new GetNotificationByRecipientIdInputModel(recipientId,
                createdAt.orElse(LocalDateTime.now()));
        return new SliceInput<>(request, pageNumber, limit);
    }
}
