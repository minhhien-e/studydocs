package studydocs.media.infrastructure.adapter.storage;

import org.springframework.stereotype.Component;
import studydocs.media.application.port.out.storage.AssetTempStoragePort;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileSystemTempStorageAdapter implements AssetTempStoragePort {

    @Override
    public Path store(InputStream content, String originalFileName) {
        try {
            Path tempDir = Path.of("temp-uploads");
            if (!Files.exists(tempDir)) {
                Files.createDirectories(tempDir);
            }
            Path tempFile = tempDir.resolve("upload-" + System.currentTimeMillis() + "-" + originalFileName);
            Files.copy(content, tempFile, StandardCopyOption.REPLACE_EXISTING);
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store temporary file", e);
        }
    }

    @Override
    public void delete(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.warn("Failed to delete temporary file: {} {}", path, e.getMessage());
        }
    }
}
