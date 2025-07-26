package studydocs.notificationservice.shared.exception.concrete.recipient;

import studydocs.notificationservice.shared.exception.abstracts.ResourceNotFoundException;

import java.util.UUID;

public class RecipientNotFoundException extends ResourceNotFoundException {
    public RecipientNotFoundException(UUID recipientId) {
        super("Thông tin nhận thông báo với ID người nhận '" + recipientId + "'");
    }

    public RecipientNotFoundException(UUID id, UUID recipientId) {
        super("Thông tin nhận thông báo với ID người nhận '" + recipientId + "' và ID '" + id + "'");
    }
}
