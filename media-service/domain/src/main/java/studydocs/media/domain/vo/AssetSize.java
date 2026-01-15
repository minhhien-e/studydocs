package studydocs.media.domain.vo;

import io.github.ddd.core.vo.ValueObject;
import studydocs.media.domain.exception.asset.InvalidAssetSizeException;

public record AssetSize(Long value) implements ValueObject {
    private static final long MAX_SIZE = 50 * 1024 * 1024; // 50MB
    private static final long MIN_SIZE = 1; // 1 byte

    public AssetSize {
        if (value == null) {
            throw InvalidAssetSizeException.nullSize();
        }
        if (value < MIN_SIZE) {
            throw InvalidAssetSizeException.lessThanMin(value, MIN_SIZE);
        }
        if (value > MAX_SIZE) {
            throw InvalidAssetSizeException.exceedsMax(value, MAX_SIZE);
        }
    }

    public static AssetSize of(Long size) {
        return new AssetSize(size);
    }
}
