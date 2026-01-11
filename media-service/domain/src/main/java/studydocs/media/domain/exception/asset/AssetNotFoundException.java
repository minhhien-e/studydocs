package studydocs.media.domain.exception.asset;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

import java.util.UUID;

public class AssetNotFoundException extends DomainException {
    public AssetNotFoundException(UUID id) {
        super("Asset not found: " + id, DomainErrorCode.FILE_NOT_FOUND);
    }
}
