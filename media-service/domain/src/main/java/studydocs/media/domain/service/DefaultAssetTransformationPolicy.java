package studydocs.media.domain.service;

import io.github.ddd.core.annotation.DomainService;
import studydocs.media.domain.enums.FileExtension;
import studydocs.media.domain.policy.AssetTransformationPolicy;

@DomainService
public class DefaultAssetTransformationPolicy implements AssetTransformationPolicy {
    @Override
    public boolean canTransform(FileExtension extension) {
        return extension == FileExtension.DOCX ||
                extension == FileExtension.PPT ||
                extension == FileExtension.PPTX ||
                extension == FileExtension.XLS ||
                extension == FileExtension.XLSX;
    }
}
