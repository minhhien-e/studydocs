package studydocs.media.application.port.out.transformation;

import studydocs.media.application.dto.transformation.TransformationResult;

import java.nio.file.Path;

public interface AssetTransformationPort {
    TransformationResult transform(Path source, String originalFileName);
}
