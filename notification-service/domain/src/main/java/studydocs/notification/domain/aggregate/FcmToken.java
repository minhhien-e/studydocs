package studydocs.notification.domain.aggregate;

import io.github.domain.aggregate.AggregateRoot;
import studydocs.notification.domain.vo.FcmTokenValue;

import java.util.UUID;

public class FcmToken extends AggregateRoot {
    private FcmTokenValue value;
    private UUID userId;

    /// Constructor
    private FcmToken(UUID id, long version) {
        super(id,version);
    }

    private FcmToken() {
        super();
    }

    /// Factory method
    public static FcmToken reconstruct(UUID id, long version, UUID userId, String value) {
        var entity = new FcmToken(id,version);
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
