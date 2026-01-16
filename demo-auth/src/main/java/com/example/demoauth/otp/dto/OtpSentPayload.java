package studydocs.notification.publisher.otp.dto;

import java.util.UUID;

public record OtpSentPayload(UUID userId, String email) {
}
