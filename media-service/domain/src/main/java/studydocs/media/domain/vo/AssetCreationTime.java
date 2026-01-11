package studydocs.media.domain.vo;

import io.github.ddd.core.vo.ValueObject;

import studydocs.media.domain.exception.asset.InvalidAssetCreationTimeException;

import java.time.LocalDateTime;

public record AssetCreationTime(LocalDateTime value) implements ValueObject {

    public AssetCreationTime {
        if (value == null) {
            throw InvalidAssetCreationTimeException.nullTime();
        }
    }

    public static AssetCreationTime of(LocalDateTime time) {
        return new AssetCreationTime(time);
    }

    public static AssetCreationTime now() {
        return new AssetCreationTime(LocalDateTime.now());
    }
}
