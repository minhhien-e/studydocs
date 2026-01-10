package studydocs.media.domain.service;

import studydocs.media.domain.enums.FileExtension;
import studydocs.media.domain.policy.AssetSupportPolicy;
import studydocs.media.domain.exception.file.InvalidAssetFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

public class DefaultAssetSupportPolicy implements AssetSupportPolicy {
    @Override
    public FileExtension supports(String assetName, Supplier<InputStream> contentProvider) {
        // 1. Check extension from name
        FileExtension extension = FileExtension.fromFileName(assetName);

        // 2. Check magic numbers
        try (InputStream is = contentProvider.get()) {
            byte[] header = new byte[8]; // Read first 8 bytes
            int bytesRead = is.read(header);

            if (bytesRead != -1 && !extension.validateSignature(header)) {
                // If validation fails, it means the content doesn't match the extension
                throw new InvalidAssetFormatException();
            }
        } catch (IOException e) {
            // If we can't read the file, valid or not is unknown, but safest to fail
            throw new RuntimeException("Failed to validate asset signature", e);
        }

        return extension;
    }
}
