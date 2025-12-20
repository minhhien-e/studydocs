package studydocs.notification.domain.aggregate;

import io.github.domain.aggregate.base.AggregateRoot;
import studydocs.notification.domain.vo.FcmTokenValue;

import java.util.UUID;

public class FcmToken extends AggregateRoot {
    private FcmTokenValue value;
    private UUID userId;

    /// Constructor
    private FcmToken(UUID id) {
        super(id);
    }

    private FcmToken() {
        super();
    }

    /// Factory method
    public static FcmToken reconstruct(UUID id, UUID userId, String value) {
        var entity = new FcmToken(id);
        entity.userId = userId;
        entity.value = new FcmTokenValue(value);
        return entity;
    }

    public static FcmToken create(UUID userId, String value) {
        var entity = new FcmToken();
        entity.userId = userId;
        entity.value = new FcmTokenValue(value);
        return entity;
    }

    /// Getter
    public FcmTokenValue getValue() {
        return this.value;
    }
    public UUID getUserId() {
        return this.userId;
    }
}
