package studydocs.media.domain.service;

import io.github.ddd.core.annotation.DomainService;
import studydocs.media.domain.enums.FileExtension;
import studydocs.media.domain.policy.AssetSupportPolicy;
import studydocs.media.domain.exception.asset.InvalidAssetFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

@DomainService
public class DefaultAssetSupportPolicy implements AssetSupportPolicy {
    @Override
    public FileExtension supports(String assetName, Supplier<InputStream> contentProvider) {
        FileExtension extension = FileExtension.fromFileName(assetName);

        try (InputStream is = contentProvider.get()) {
            byte[] header = is.readNBytes(8);

            if (header.length > 0 && !extension.validateSignature(header)) {
                throw new InvalidAssetFormatException();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to validate asset signature", e);
        }

        return extension;
    }
}
