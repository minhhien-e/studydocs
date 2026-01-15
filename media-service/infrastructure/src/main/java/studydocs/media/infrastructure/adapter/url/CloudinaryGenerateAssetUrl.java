package studydocs.media.infrastructure.adapter.url;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.media.application.dto.payload.GenerateDownloadUrlPayload;
import studydocs.media.application.dto.payload.GeneratePreviewUrlPayLoad;
import studydocs.media.application.dto.projection.PreviewData;
import studydocs.media.application.port.in.url.GenerateAssetUrlPort;
import studydocs.media.domain.exception.asset.AssetNotFoundException;
import studydocs.media.infrastructure.persistence.repository.MongoDataAssetRepository;

@Component
@RequiredArgsConstructor
public class CloudinaryGenerateAssetUrl implements GenerateAssetUrlPort {
    private final Cloudinary cloudinary;
    private final MongoDataAssetRepository mongoDataAssetRepository;

    @Override
    public String generateDownloadUrl(GenerateDownloadUrlPayload payload) {
        var entity = mongoDataAssetRepository.findById(payload.fileId())
                .orElseThrow(() -> new AssetNotFoundException(payload.fileId()));
        return cloudinary.url()
                .resourceType(entity.getResourceType())
                .publicId(entity.getPublicId())
                .secure(true)
                .transformation(new Transformation().flags("attachment"))
                .generate();
    }

    @Override
    public PreviewData generatePreviewUrl(GeneratePreviewUrlPayLoad payload) {
        var entity = mongoDataAssetRepository.findById(payload.fileId())
                .orElseThrow(() -> new AssetNotFoundException(payload.fileId()));

        if (entity.getPublicId() == null) {
            return null;
        }

        // Use a safe placeholder for page number
        String pagePlaceholder = "PAGE_NUMBER_PLACEHOLDER";
        
        String url = cloudinary.url()
                .resourceType(entity.getResourceType())
                .publicId(entity.getPublicId())
                .secure(true)
                .transformation(
                        new Transformation()
                                .rawTransformation("pg_" + pagePlaceholder))
                .generate();

        if (url == null) {
            return null;
        }

        return new PreviewData(url, pagePlaceholder);
    }
}
