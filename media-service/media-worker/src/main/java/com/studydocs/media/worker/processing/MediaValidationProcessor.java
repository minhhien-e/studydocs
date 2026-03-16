package com.studydocs.media.worker.processing;

import com.studydocs.media.core.model.entity.MediaProcessingJob;
import com.studydocs.media.core.model.enums.ProcessingJobStatus;
import com.studydocs.media.core.model.enums.ProcessingJobType;
import com.studydocs.media.core.model.event.MediaProcessingFailedEvent;
import com.studydocs.media.core.model.event.MediaValidatedEvent;
import com.studydocs.media.core.model.event.MediaVirusScanStartedEvent;
import com.studydocs.media.core.publisher.InternalEventPublisher;
import com.studydocs.media.core.storage.StorageProvider;
import com.studydocs.media.core.repository.MediaAssetRepository;
import com.studydocs.media.core.repository.MediaProcessingJobRepository;
import com.studydocs.media.infrastructure.utils.MimeTypeDetector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Transactional
public class MediaValidationProcessor {
    private final MediaAssetRepository mediaAssetRepository;
    private final MediaProcessingJobRepository mediaProcessingJobRepository;
    private final StorageProvider storageProvider;
    private final InternalEventPublisher eventPublisher;

    public void process(UUID mediaId) {
        try {
            var mediaAsset = mediaAssetRepository.findById(mediaId);

            var job = MediaProcessingJob.builder()
                .asset(mediaAsset)
                .jobType(ProcessingJobType.FILE_SIGNATURE_VALIDATION)
                .status(ProcessingJobStatus.RUNNING)
                .build();
            mediaProcessingJobRepository.save(job);

            try (var fileStream = storageProvider.download(mediaAsset.getOriginalKey())) {
                var mimeType = MimeTypeDetector.detect(fileStream);

                eventPublisher.publish(new MediaValidatedEvent(
                    mediaId,
                    mimeType,
                    mimeType.equalsIgnoreCase(mediaAsset.getMimeType()),
                    Instant.now()
                ));
            }
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
            eventPublisher.publish(MediaProcessingFailedEvent.builder()
                .mediaId(mediaId)
                .jobType(ProcessingJobType.FILE_SIGNATURE_VALIDATION)
                .errorMessage(msg)
                .occurredAt(Instant.now())
                .build());
        }
    }

    public void processSuccessful(MediaValidatedEvent event) {
        try {
            var mediaAsset = mediaAssetRepository.findById(event.mediaId());
            var job = mediaProcessingJobRepository.findByStatusAndAssetId(ProcessingJobStatus.RUNNING, event.mediaId());

            mediaAsset.setMimeType(event.detectedMimeType());
            job.setStatus(ProcessingJobStatus.SUCCESS);

            mediaProcessingJobRepository.save(job);
            mediaAssetRepository.save(mediaAsset);
            eventPublisher.publish(new MediaVirusScanStartedEvent(event.mediaId(), Instant.now()));
        } catch (Exception e) {
            eventPublisher.publish(MediaProcessingFailedEvent.builder()
                .mediaId(event.mediaId())
                .jobType(ProcessingJobType.FILE_SIGNATURE_VALIDATION)
                .errorMessage(e.getMessage())
                .occurredAt(Instant.now())
                .build());
        }
    }
}
