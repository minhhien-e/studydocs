package studydocs.media.application.port.out.processing;

import studydocs.media.domain.enums.FileExtension;

import java.io.InputStream;

public interface PageCounterPort {
    int countPages(InputStream inputStream);

    default int countPages(java.nio.file.Path path) {
        try (InputStream is = java.nio.file.Files.newInputStream(path)) {
            return countPages(is);
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to read file for counting pages", e);
        }
    }

    boolean supports(FileExtension ext);
}
