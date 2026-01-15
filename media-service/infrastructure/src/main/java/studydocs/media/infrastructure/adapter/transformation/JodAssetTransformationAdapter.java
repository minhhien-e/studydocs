package studydocs.media.infrastructure.adapter.transformation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jodconverter.core.office.OfficeManager;
import org.jodconverter.local.LocalConverter;
import org.springframework.stereotype.Component;
import studydocs.media.application.dto.transformation.TransformationResult;
import studydocs.media.application.port.out.transformation.AssetTransformationPort;

import java.io.File;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
@Slf4j
public class JodAssetTransformationAdapter implements AssetTransformationPort {

    private final OfficeManager officeManager;

    @Override
    public TransformationResult transform(Path sourcePath, String originalFileName) {
        String targetExtension = "pdf";
        File source = sourcePath.toFile();
        // The physical target file uses the physical source name to maintain uniqueness/temp location structure
        File target = new File(source.getParentFile(), 
                source.getName().replaceFirst("[.][^.]+$", "") + "." + targetExtension);

        try {
            log.info("Starting transformation from {} to {}", source.getName(), target.getName());
            long startTime = System.currentTimeMillis();
            
            LocalConverter.builder()
                    .officeManager(officeManager)
                    .build()
                    .convert(source)
                    .to(target)
                    .execute();
            
            log.info("Transformation completed in {}ms", System.currentTimeMillis() - startTime);
            
            // Calculate new logical filename
            String newLogicalName = originalFileName.replaceFirst("[.][^.]+$", "") + "." + targetExtension;

            return new TransformationResult(
                target.toPath(),
                newLogicalName,
                "application/pdf",
                target.length()
            );
        } catch (Exception e) {
            log.error("Failed to transform file: {}", source.getName(), e);
            throw new RuntimeException("Transformation failed", e);
        }
    }
}
