package studydocs.media.domain.policy;

import studydocs.media.domain.enums.FileExtension;

import java.io.InputStream;
import java.util.function.Supplier;

public interface AssetSupportPolicy {
    FileExtension supports(String extension, Supplier<InputStream> contentProvider);
}
