package studydocs.media.application.dto.transformation;

import java.nio.file.Path;

public record TransformationResult(
        Path file,
        String fileName,
        String contentType,
        long fileSize
) {}
