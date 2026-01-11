package studydocs.media.domain.exception.asset;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class AssetNotSupportedException extends DomainException {
    public AssetNotSupportedException(String extension) {
        super("Asset with extension " + extension + " is not supported", DomainErrorCode.FILE_NOT_SUPPORTED);
    }
}
