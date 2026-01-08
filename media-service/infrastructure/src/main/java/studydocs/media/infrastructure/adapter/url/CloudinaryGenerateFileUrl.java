        package studydocs.media.infrastructure.adapter.url;

        import com.cloudinary.Cloudinary;
        import com.cloudinary.Transformation;
        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Component;
        import studydocs.media.application.dto.payload.GenerateDownloadUrlPayload;
        import studydocs.media.application.dto.payload.GeneratePreviewUrlPayLoad;
        import studydocs.media.application.dto.projection.PreviewData;
        import studydocs.media.application.port.in.url.GenerateFileUrlPort;
        import studydocs.media.domain.exception.file.FileNotFoundException;
        import studydocs.media.infrastructure.persistence.repository.MongoDataFileRepository;

        @Component
        @RequiredArgsConstructor
        public class CloudinaryGenerateFileUrl implements GenerateFileUrlPort {
            private final Cloudinary cloudinary;
            private final MongoDataFileRepository mongoDataFileRepository;

            @Override
            public String generateDownloadUrl(GenerateDownloadUrlPayload payload) {
                var entity = mongoDataFileRepository.findById(payload.fileId()).orElseThrow(() -> new FileNotFoundException(payload.fileId()));
                return cloudinary.url()
                        .resourceType(entity.getResourceType())
                        .publicId(entity.getPublicId())
                        .secure(true)
                        .transformation(new Transformation().flags("attachment"))
                        .generate();
            }

            @Override
            public PreviewData generatePreviewUrl(GeneratePreviewUrlPayLoad payload) {
                var entity = mongoDataFileRepository.findById(payload.fileId()).orElseThrow(() -> new FileNotFoundException(payload.fileId()));
                String url = cloudinary.url()
                        .resourceType("image")
                        .publicId(entity.getPublicId())
                        .secure(true)
                        .transformation(
                                new Transformation()
                                        .page(1)
                                        .fetchFormat("jpg")
                        )
                        .generate();
                var previewUrl = url.replace("pg_1", "pg_$pageNumber");
                return new PreviewData(previewUrl, "$pageNumber");
            }
        }
