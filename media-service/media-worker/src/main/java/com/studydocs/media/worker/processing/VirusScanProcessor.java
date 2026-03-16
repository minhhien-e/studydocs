package com.studydocs.media.worker.processing;

import com.studydocs.media.core.antivirus.VirusScanner;
import com.studydocs.media.core.model.entity.MediaProcessingJob;
import com.studydocs.media.core.model.enums.AssetState;
import com.studydocs.media.core.model.enums.ProcessingJobStatus;
import com.studydocs.media.core.model.enums.ProcessingJobType;
import com.studydocs.media.core.model.event.MediaProcessingFailedEvent;
import com.studydocs.media.core.model.event.MediaVirusScanCompletedEvent;
import com.studydocs.media.core.publisher.InternalEventPublisher;
import com.studydocs.media.core.repository.MediaAssetRepository;
import com.studydocs.media.core.repository.MediaProcessingJobRepository;
import com.studydocs.media.core.storage.StorageProvider;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class VirusScanProcessor {

    private final MediaAssetRepository mediaAssetRepository;
    private final MediaProcessingJobRepository mediaProcessingJobRepository;
    private final StorageProvider storageProvider;
    private final VirusScanner virusScanner;
    private final InternalEventPublisher eventPublisher;
    private final MeterRegistry meterRegistry;

    public void process(UUID mediaId) {
        try {
            var mediaAsset = mediaAssetRepository.findById(mediaId);

            var job = MediaProcessingJob.builder()
                .asset(mediaAsset)
                .jobType(ProcessingJobType.VIRUS_SCAN)
                .status(ProcessingJobStatus.RUNNING)
                .build();
            mediaProcessingJobRepository.save(job);

            try (var fileStream = storageProvider.download(mediaAsset.getOriginalKey())) {
                var scanResult = virusScanner.scan(fileStream);

                if (scanResult.isSafe()) {
                    eventPublisher.publish(new MediaVirusScanCompletedEvent(
                        mediaId,
                        false,
                        null,
                        Instant.now()
                    ));
                } else {
                    meterRegistry.counter("media.virus.detected", "status", "blocked").increment();
                    eventPublisher.publish(new MediaVirusScanCompletedEvent(
                        mediaId,
                        true,
                        scanResult.virusName(),
                        Instant.now()
                    ));
                }
            }
        } catch (Exception e) {
            eventPublisher.publish(MediaProcessingFailedEvent.builder()
                .mediaId(mediaId)
                .jobType(ProcessingJobType.VIRUS_SCAN)
                .errorMessage(e.getMessage())
                .occurredAt(Instant.now())
                .build());
        }
    }

    public void processSuccessful(MediaVirusScanCompletedEvent event) {
        try {
            var job = mediaProcessingJobRepository.findByStatusAndAssetId(ProcessingJobStatus.RUNNING, event.mediaId());
            job.setStatus(ProcessingJobStatus.SUCCESS);
            var mediaAsset = mediaAssetRepository.findById(event.mediaId());
            if (event.infected()) {
                mediaAsset.setState(AssetState.FAILED);
                mediaAsset.setRejectReason("Virus detected: " + event.virusName());
            } else {
                mediaAsset.setState(AssetState.ACTIVE);
            }
            mediaAssetRepository.save(mediaAsset);
            mediaProcessingJobRepository.save(job);

            // public event đã virus scan xong, các processor khác có thể xử lý tiếp
        } catch (Exception e) {
            eventPublisher.publish(MediaProcessingFailedEvent.builder()
                .mediaId(event.mediaId())
                .jobType(ProcessingJobType.VIRUS_SCAN)
                .errorMessage(e.getMessage())
                .occurredAt(Instant.now())
                .build());
        }
    }
}
