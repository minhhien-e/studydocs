package studydocs.media.domain.policy;

import studydocs.media.domain.enums.FileExtension;

public interface AssetTransformationPolicy {

    boolean canTransform(FileExtension extension);
}
