package studydocs.media.domain.vo;

import io.github.ddd.core.vo.ValueObject;
import studydocs.media.domain.exception.file.InvalidAssetContentTypeException;

public record AssetContentType(String value) implements ValueObject {

    public AssetContentType {
        if (value == null || value.trim().isEmpty()) {
            throw InvalidAssetContentTypeException.nullOrEmpty();
        }
        value = value.trim();
    }

    public static AssetContentType of(String contentType) {
        return new AssetContentType(contentType);
    }
}
