package studydocs.notification.domain.exception.recipient;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RecipientsNotFoundException extends DomainException {
    public RecipientsNotFoundException(List<UUID> ids) {
        super("Some recipients were not found: " + Arrays.toString(ids.toArray()), DomainErrorCode.RECIPIENTS_NOT_FOUND);
    }
}
