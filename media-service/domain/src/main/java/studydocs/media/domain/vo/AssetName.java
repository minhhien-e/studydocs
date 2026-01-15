package studydocs.media.domain.vo;

import io.github.ddd.core.vo.ValueObject;
import studydocs.media.domain.exception.asset.InvalidAssetNameException;

public record AssetName(String value) implements ValueObject {
    private static final int MAX_LENGTH = 255;

    public AssetName {
        if (value == null || value.trim().isEmpty()) {
            throw InvalidAssetNameException.nullOrEmpty();
        }
        value = value.trim();
        if (value.length() > MAX_LENGTH) {
            throw InvalidAssetNameException.lengthExceeded(value, MAX_LENGTH);
        }
    }

    public static AssetName of(String assetName) {
        return new AssetName(assetName);
    }
}
